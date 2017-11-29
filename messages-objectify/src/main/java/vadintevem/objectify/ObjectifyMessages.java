package vadintevem.objectify;

import vadintevem.entities.Message;
import vadintevem.messages.Messages;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ObjectifyMessages implements Messages {

    private final Random generator = new Random(Instant.now().toEpochMilli());

    public ObjectifyMessages() {
        ofy().save().entity(MessageEntity.from(Message.of("Carpe diem")));
        ofy().save().entity(MessageEntity.from(Message.of("Le ciel, c'est les autres")));
        ofy().save().entity(MessageEntity.from(Message.of("I have always found that plans are useless, but planning is indispensable")));
    }

    @Override
    public void save(Message message) {
        ofy().save().entity(MessageEntity.from(message)).now();
    }

    @Override
    public Optional<Message> find() {
        return Optional.ofNullable(ofy().load().type(MessageEntity.class).list()
                .get(Math.abs(generator.nextInt()) % 3));
    }
}
