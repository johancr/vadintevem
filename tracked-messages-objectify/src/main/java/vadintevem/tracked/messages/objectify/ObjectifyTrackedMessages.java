package vadintevem.tracked.messages.objectify;

import com.googlecode.objectify.Ref;
import vadintevem.entities.Message;
import vadintevem.history.objectify.HistoryEntity;
import vadintevem.messages.objectify.MessageEntity;
import vadintevem.tracked.messages.TrackedMessages;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static java.util.stream.Collectors.toList;

public class ObjectifyTrackedMessages implements TrackedMessages {

    @Override
    public Optional<Message> find() {
        HistoryEntity history = getHistory();
        Collection<MessageEntity> messages = ofy().load().type(MessageEntity.class).list();

        return findFirstUnread(messages, history)
                .map(MessageEntity::toDomain);
    }

    private HistoryEntity getHistory() {
        HistoryEntity history = ofy().load().type(HistoryEntity.class).first().now();
        return history != null
                ? history
                : new HistoryEntity();
    }

    private static Optional<MessageEntity> findFirstUnread(Collection<MessageEntity> messages, HistoryEntity history) {
        return messages.stream().filter(notIn(history)).findFirst();
    }

    private static Predicate<MessageEntity> notIn(HistoryEntity history) {
        Collection<MessageEntity> read = history.getHistory().stream().map(Ref::get).collect(toList());
        return message -> !read.contains(message);
    }
}
