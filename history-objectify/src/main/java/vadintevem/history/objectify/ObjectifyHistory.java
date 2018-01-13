package vadintevem.history.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import vadintevem.entities.Message;
import vadintevem.entities.User;
import vadintevem.history.History;
import vadintevem.messages.objectify.MessageEntity;

import java.util.Collections;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static java.util.stream.Collectors.toList;

public class ObjectifyHistory implements History {

    @Override
    public List<Message> load(User user) {
        return getMessages(user);
    }

    private List<Message> getMessages(User user) {
        HistoryEntity history = ofy().load().type(HistoryEntity.class).filter("username", user.getUsername()).first().now();

        if (history != null) {
            return history.getHistory().stream()
                    .map(Ref::get)
                    .map(MessageEntity::toDomain)
                    .collect(toList());
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Message message, User user) {
        HistoryEntity history = getHistoryEntity(user);
        add(message, history);
    }

    private void add(Message message, HistoryEntity history) {
        Key<MessageEntity> messageEntity = Key.create(MessageEntity.class, message.getId());
        history.add(Ref.create(messageEntity));
        ofy().save().entity(history).now();
    }

    private static HistoryEntity getHistoryEntity(User user) {
        HistoryEntity entity = ofy().load().type(HistoryEntity.class).filter("username", user.getUsername()).first().now();
        return entity != null
                ? entity
                : new HistoryEntity(user.getUsername());
    }
}
