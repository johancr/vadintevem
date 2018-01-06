package vadintevem.reader.impl;

import vadintevem.authors.Authors;
import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.message.selector.Algorithm;
import vadintevem.message.selector.MessageSelector;
import vadintevem.message.selector.MessageSelectorFactory;
import vadintevem.ranking.Ranker;
import vadintevem.reader.FindMessageRequest;
import vadintevem.reader.NextMessageRequest;
import vadintevem.reader.ReaderInteractor;
import vadintevem.tracked.messages.TrackedMessages;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static vadintevem.base.functional.Predicates.notIn;
import static vadintevem.message.selector.Algorithm.parse;

public class DefaultReaderInteractor implements ReaderInteractor {

    private final History history;
    private final TrackedMessages trackedMessages;
    private final MessageSelectorFactory messageSelectorFactory;
    private final Ranker ranker;
    private final Authors authors;

    @Inject
    public DefaultReaderInteractor(History history,
                                   TrackedMessages trackedMessages,
                                   MessageSelectorFactory messageSelectorFactory,
                                   Ranker ranker,
                                   Authors authors) {
        this.history = history;
        this.trackedMessages = trackedMessages;
        this.messageSelectorFactory = messageSelectorFactory;
        this.ranker = ranker;
        this.authors = authors;
    }

    @Override
    public Optional<Message> findRandomMessage() {
        MessageSelector messageSelector = messageSelectorFactory.create(Algorithm.RANDOM);
        return messageSelector.select();
    }

    @Override
    public Optional<Message> findMessage(FindMessageRequest request) {
        Optional<Message> found = findMessage(parse(request.getAlgorithm()), request.getAuthor());
        found.ifPresent(updateHistory(request.getAuthor()));
        return found;
    }

    private Optional<Message> findMessage(Algorithm algorithm, Author author) {
        MessageSelector messageSelector = messageSelectorFactory.create(algorithm);
        return messageSelector.selectBasedOn(author);
    }

    private Consumer<Message> updateHistory(Author author) {
        return message -> history.add(message, author);
    }

    @Override
    public Optional<Message> nextMessage(NextMessageRequest request) {
        increaseRank(request.getPrevious());
        Optional<Message> next = nextMessage(request.getAuthor());
        next.ifPresent(updateHistory(request.getAuthor()));
        return next;
    }

    private void increaseRank(Message previous) {
        if (previous.getId() != null)
        {
            ranker.increase(previous);
        }
    }

    private Optional<Message> nextMessage(Author author) {
        return trackedMessages.filterFind(author);
    }

    @Override
    public List<Message> loadHistory(Author author) {
        List<Message> messages = history.load(author);
        return authors.findWrittenBy(author)
                .map(filterAuthored(messages))
                .fold(notFound -> Collections.emptyList(), identity());
    }

    private Function<Collection<Message>, List<Message>> filterAuthored(List<Message> messages) {
        return authored -> messages.stream().filter(notIn(authored)).collect(toList());
    }
}
