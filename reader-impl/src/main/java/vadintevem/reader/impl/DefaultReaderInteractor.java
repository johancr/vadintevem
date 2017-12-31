package vadintevem.reader.impl;

import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.message.selector.MessageSelector;
import vadintevem.message.selector.MessageSelectorFactory;
import vadintevem.ranking.Ranker;
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
    public Optional<Message> findMessage(String algorithm) {
        MessageSelector messageSelector = messageSelectorFactory.create(parse(algorithm));
        return messageSelector.select();
    }

    @Override
    public Optional<Message> nextMessage(Message previous) {
        ranker.increase(previous);
        return trackedMessages.find();
    }

    @Override
    public List<Message> loadHistory() {
        return history.load();
    }

    @Override
    public void saveHistory(Message message) {
        history.add(message);
    }
}
