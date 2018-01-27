package vadintevem.reader.impl;

import vadintevem.authors.Authors;
import vadintevem.entities.Message;
import vadintevem.entities.User;
import vadintevem.events.EventNotifier;
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
    private final EventNotifier eventNotifier;

    @Inject
    public DefaultReaderInteractor(History history,
                                   TrackedMessages trackedMessages,
                                   MessageSelectorFactory messageSelectorFactory,
                                   Ranker ranker,
                                   Authors authors,
                                   EventNotifier eventNotifier) {
        this.history = history;
        this.trackedMessages = trackedMessages;
        this.messageSelectorFactory = messageSelectorFactory;
        this.ranker = ranker;
        this.authors = authors;
        this.eventNotifier = eventNotifier;
    }

    @Override
    public Optional<Message> findRandomMessage() {
        MessageSelector messageSelector = messageSelectorFactory.create(Algorithm.RANDOM);
        return messageSelector.select();
    }

    @Override
    public Optional<Message> findMessage(FindMessageRequest request) {
        Optional<Message> found = findMessage(parse(request.getAlgorithm()), request.getUser());
        found.ifPresent(updateHistory(request.getUser()));
        return found;
    }

    private Optional<Message> findMessage(Algorithm algorithm, User user) {
        MessageSelector messageSelector = messageSelectorFactory.create(algorithm);
        return messageSelector.selectBasedOn(user);
    }

    private Consumer<Message> updateHistory(User user) {
        return message -> history.update(message, user);
    }

    @Override
    public Optional<Message> nextMessage(NextMessageRequest request) {
        increaseRank(request.getPrevious());
        Optional<Message> next = nextMessage(request.getUser());
        next.ifPresent(updateHistory(request.getUser()));
        notifyAuthorOf(request.getPrevious());
        return next;
    }

    private void notifyAuthorOf(Message previous) {
        if (previous.getId() != null) {
            Optional<User> author = authors.findAuthorOf(previous);
            author.ifPresent(a -> eventNotifier.notify(new MessageReadEvent(a)));
        }
    }

    private void increaseRank(Message previous) {
        if (previous.getId() != null)
        {
            ranker.increase(previous);
        }
    }

    private Optional<Message> nextMessage(User user) {
        return trackedMessages.filterFind(user);
    }

    @Override
    public List<Message> loadHistory(User user) {
        List<Message> messages = history.load(user);
        return authors.findWrittenBy(user)
                .map(filterAuthored(messages))
                .fold(notFound -> messages, identity());
    }

    private Function<Collection<Message>, List<Message>> filterAuthored(List<Message> messages) {
        return authored -> messages.stream().filter(notIn(authored)).collect(toList());
    }
}
