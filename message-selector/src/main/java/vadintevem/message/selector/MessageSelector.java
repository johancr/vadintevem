package vadintevem.message.selector;

import vadintevem.entities.User;
import vadintevem.entities.Message;

import java.util.Optional;

public interface MessageSelector {

    Optional<Message> select();

    Optional<Message> selectBasedOn(User user);
}
