package vadintevem.message.selector.impl;

import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.message.selector.MessageSelector;
import vadintevem.tracked.messages.TrackedMessages;

import javax.inject.Inject;
import java.util.Optional;

public class UnreadMessageSelector implements MessageSelector {

    private final TrackedMessages trackedMessages;

    @Inject
    public UnreadMessageSelector(TrackedMessages trackedMessages) {
        this.trackedMessages = trackedMessages;
    }

    @Override
    public Optional<Message> select() {
        return Optional.empty();
    }

    @Override
    public Optional<Message> selectBasedOn(Author author) {
        return trackedMessages.filterFind(author);
    }
}
