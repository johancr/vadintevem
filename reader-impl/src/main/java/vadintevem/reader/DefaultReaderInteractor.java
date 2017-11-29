package vadintevem.reader;

import vadintevem.entities.Message;
import vadintevem.messages.Messages;

import javax.inject.Inject;
import java.util.Optional;

public class DefaultReaderInteractor implements ReaderInteractor {

    private final Messages messages;

    @Inject
    public DefaultReaderInteractor(Messages messages) {
        this.messages = messages;
    }

    @Override
    public Optional<Message> findMessage() {
        return messages.find();
    }
}
