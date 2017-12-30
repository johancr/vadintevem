package vadintevem.messages;

import vadintevem.entities.Message;

import java.util.Collection;
import java.util.Optional;

public interface Messages {

    long save(Message message);

    Optional<Message> find();

    Collection<Message> findAll();
}
