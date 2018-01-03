package vadintevem.reader.impl;

import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.message.selector.MessageSelector;
import vadintevem.message.selector.MessageSelectorFactory;
import vadintevem.ranking.Ranker;
import vadintevem.reader.FindMessageRequest;
import vadintevem.reader.NextMessageRequest;
import vadintevem.reader.ReaderInteractor;
import vadintevem.tracked.messages.TrackedMessages;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static vadintevem.message.selector.Algorithm.parse;

public class DefaultReaderInteractor implements ReaderInteractor {

    private final History history;
    private final TrackedMessages trackedMessages;
    private final MessageSelectorFactory messageSelectorFactory;
    private final Ranker ranker;

    @Inject
    public DefaultReaderInteractor(History history,
                                   TrackedMessages trackedMessages,
                                   MessageSelectorFactory messageSelectorFactory,
                                   Ranker ranker) {
        this.history = history;
        this.trackedMessages = trackedMessages;
        this.messageSelectorFactory = messageSelectorFactory;
        this.ranker = ranker;
    }

    @Override
    public Optional<Message> findMessage() {
        return trackedMessages.find();
    }

    @Override
    public Optional<Message> findMessage(Author author) {
        return trackedMessages.filterFind(author);
    }

    @Override
    public Optional<Message> findMessage(String algorithm, Author author) {
        MessageSelector messageSelector = messageSelectorFactory.create(parse(algorithm));
        return messageSelector.selectBasedOn(author);
    }

    @Override
    public Optional<Message> findMessage(FindMessageRequest request) {
        if (request.getPrevious().getId() != null)
        {
            history.add(request.getPrevious(), request.getAuthor());
        }
        MessageSelector messageSelector = messageSelectorFactory.create(parse(request.getAlgorithm()));
        return messageSelector.selectBasedOn(request.getAuthor());
    }

    @Override
    public Optional<Message> nextMessage(NextMessageRequest request) {
        if (request.getPrevious().getId() != null)
        {
            ranker.increase(request.getPrevious());
            history.add(request.getPrevious(), request.getAuthor());
        }
        return trackedMessages.filterFind(request.getAuthor());
    }

    @Override
    public List<Message> loadHistory() {
        return history.load();
    }
}
