package vadintevem.message.selector;

import vadintevem.entities.Author;
import vadintevem.entities.Message;

import java.util.Optional;

public interface MessageSelector {

    Optional<Message> select();

    Optional<Message> selectBasedOn(Author author);
}
