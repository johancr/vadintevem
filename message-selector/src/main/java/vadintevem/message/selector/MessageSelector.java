package vadintevem.message.selector;

import vadintevem.entities.Message;

import java.util.Optional;

public interface MessageSelector {

    Optional<Message> select();
}
