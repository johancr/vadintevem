package vadintevem.tracked.messages.impl;

import vadintevem.authors.Authors;
import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.messages.Messages;
import vadintevem.tracked.messages.TrackedMessages;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public class DefaultTrackedMessages implements TrackedMessages {

    private final Messages messages;
    private final History history;
    private final Authors authors;

    @Inject
    public DefaultTrackedMessages(Messages messages, History history, Authors authors) {
        this.messages = messages;
        this.history = history;
        this.authors = authors;
    }

    @Override
    public Optional<Message> find() {
        return findFirstUnread(messages.findAll(), history.load());
    }

    @Override
    public Optional<Message> filterFind(Author author) {
        return authors.findWrittenBy(author)
                .fold(error -> find(),
                        authored -> findFirstUnread(messages.findAll(), history.load(), authored));
    }

    private static Optional<Message> findFirstUnread(Collection<Message> messages, Collection<Message> history, Collection<Message> authored) {
        return messages.stream().filter(notIn(history).and(notIn(authored))).findFirst();
    }

    private static Optional<Message> findFirstUnread(Collection<Message> messages, Collection<Message> history) {
        return messages.stream().filter(notIn(history)).findFirst();
    }

    private static Predicate<Message> notIn(Collection<Message> messages) {
        return message -> !messages.contains(message);
    }
}
