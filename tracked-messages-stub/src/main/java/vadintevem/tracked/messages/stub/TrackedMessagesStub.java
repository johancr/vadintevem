package vadintevem.tracked.messages.stub;

import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.messages.Messages;
import vadintevem.tracked.messages.TrackedMessages;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public class TrackedMessagesStub implements TrackedMessages {

    private final Messages messages;
    private final History history;

    @Inject
    public TrackedMessagesStub(Messages messages, History history) {
        this.messages = messages;
        this.history = history;
    }

    @Override
    public Optional<Message> find() {
        Collection<Message> read = history.load();
        return findFirstUnread(messages, read);
    }

    private static Optional<Message> findFirstUnread(Messages messages, Collection<Message> read) {
        return messages.findAll().stream().filter(notInHistory(read)).findFirst();
    }

    private static Predicate<Message> notInHistory(Collection<Message> read) {
        return message -> !read.contains(message);
    }
}
