package vadintevem.objectify;

import vadintevem.entities.Message;
import vadintevem.messages.Messages;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ObjectifyMessages implements Messages {

    private final Random generator = new Random(Instant.now().toEpochMilli());

    @Override
    public void save(Message message) {
        ofy().save().entity(MessageEntity.from(message)).now();
    }

    @Override
    public Optional<Message> find() {
        return Optional.ofNullable(ofy().load().type(MessageEntity.class).list()
                .get(Math.abs(generator.nextInt()) % ofy().load().type(MessageEntity.class).list().size()));
    }
}
