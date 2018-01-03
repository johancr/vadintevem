package vadintevem.history.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.messages.objectify.MessageEntity;

import java.util.Collections;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static java.util.stream.Collectors.toList;

public class ObjectifyHistory implements History {

    @Override
    public List<Message> load() {
        return getMessages(Author.of("unknown"));
    }

    @Override
    public List<Message> load(Author author) {
        return getMessages(author);
    }

    private List<Message> getMessages(Author author) {
        HistoryEntity history = ofy().load().type(HistoryEntity.class).filter("author", author.getId()).first().now();

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
    public void add(Message message) {
        HistoryEntity history = getHistoryEntity(Author.of("unknown"));
        add(message, history);
    }

    @Override
    public void add(Message message, Author author) {
        HistoryEntity history = getHistoryEntity(author);
        add(message, history);
    }

    private void add(Message message, HistoryEntity history) {
        Key<MessageEntity> messageEntity = Key.create(MessageEntity.class, message.getId());
        history.add(Ref.create(messageEntity));
        ofy().save().entity(history).now();
    }

    private static HistoryEntity getHistoryEntity(Author author) {
        HistoryEntity entity = ofy().load().type(HistoryEntity.class).filter("author", author.getId()).first().now();
        return entity != null
                ? entity
                : new HistoryEntity(author.getId());
    }
}
