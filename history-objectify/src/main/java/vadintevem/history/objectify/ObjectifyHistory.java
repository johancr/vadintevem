package vadintevem.history.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.messages.objectify.MessageEntity;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static java.util.stream.Collectors.toList;

public class ObjectifyHistory implements History {

    @Override
    public List<Message> load() {
        return ofy().load().type(HistoryEntity.class).first().now().getHistory().stream()
                .map(Ref::get).collect(toList());
    }

    @Override
    public void add(Message message) {
        HistoryEntity history = getHistoryEntity();
        Key<MessageEntity> messageEntity = Key.create(MessageEntity.class, message.getId());
        history.add(Ref.create(messageEntity));
        ofy().save().entity(history).now();
    }

    private HistoryEntity getHistoryEntity() {
        HistoryEntity entity = ofy().load().type(HistoryEntity.class).first().now();
        return entity != null
                ? entity
                : new HistoryEntity();
    }
}
