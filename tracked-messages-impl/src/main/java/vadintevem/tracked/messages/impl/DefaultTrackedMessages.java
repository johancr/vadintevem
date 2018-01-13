package vadintevem.tracked.messages.impl;

import vadintevem.authors.Authors;
import vadintevem.entities.User;
import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.messages.Messages;
import vadintevem.tracked.messages.TrackedMessages;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

import static vadintevem.base.functional.Predicates.notIn;

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
    public Optional<Message> filterFind(User user) {
        return authors.findWrittenBy(user)
                .fold(notFound -> findUnread(messages.findAll(), history.load(user)),
                        authored -> findUnread(messages.findAll(), history.load(user), authored));
    }

    private static Optional<Message> findUnread(Collection<Message> messages, Collection<Message> history) {
        return messages.stream().filter(notIn(history)).findFirst();
    }

    private static Optional<Message> findUnread(Collection<Message> messages, Collection<Message> history, Collection<Message> authored) {
        return messages.stream().filter(notIn(history).and(notIn(authored))).findFirst();
    }
}
