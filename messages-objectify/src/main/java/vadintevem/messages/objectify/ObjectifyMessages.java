package vadintevem.messages.objectify;

import com.googlecode.objectify.Key;
import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.messages.Messages;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static java.util.stream.Collectors.toList;

public class ObjectifyMessages implements Messages {

    private final Random generator = new Random(Instant.now().toEpochMilli());

    @Override
    public long save(Message message) {
        Key<MessageEntity> saved = ofy().save().entity(MessageEntity.from(message)).now();
        return saved.getId();
    }

    @Override
    public Optional<Message> find() {
        int size = ofy().load().type(MessageEntity.class).list().size();
        return size == 0
                ? Optional.empty()
                : Optional.ofNullable(ofy().load().type(MessageEntity.class).list()
                    .get(Math.abs(generator.nextInt()) % size));
    }

    @Override
    public Collection<Message> findAll() {
        return toDomain(ofy().load().type(MessageEntity.class).list());
    }

    private static Collection<Message> toDomain(Collection<MessageEntity> entities) {
        return entities.stream().map(MessageEntity::toDomain).collect(toList());
    }
}
