package vadintevem.reader.impl;

import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.messages.Messages;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class DefaultReaderInteractor implements ReaderInteractor {

    private final Messages messages;
    private final History history;

    @Inject
    public DefaultReaderInteractor(Messages messages, History history) {
        this.messages = messages;
        this.history = history;
    }

    @Override
    public Optional<Message> findMessage() {
        return messages.find();
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
