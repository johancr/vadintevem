package vadintevem.reader;

import vadintevem.entities.Message;
import vadintevem.messages.Messages;

import javax.inject.Inject;

public class DefaultReaderInteractor implements ReaderInteractor {

    private final Messages messages;

    @Inject
    public DefaultReaderInteractor(Messages messages) {
        this.messages = messages;
    }

    @Override
    public Message nextMessage() {
        return messages.next();
    }
}
