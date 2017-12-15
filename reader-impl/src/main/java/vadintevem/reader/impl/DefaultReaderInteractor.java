package vadintevem.reader.impl;

import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.messages.Messages;
import vadintevem.tracked.messages.TrackedMessages;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class DefaultReaderInteractor implements ReaderInteractor {

    private final History history;
    private final TrackedMessages trackedMessages;

    @Inject
    public DefaultReaderInteractor(History history, TrackedMessages trackedMessages) {
        this.history = history;
        this.trackedMessages = trackedMessages;
    }

    @Override
    public Optional<Message> findMessage() {
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
