package vadintevem.authors;

import vadintevem.base.functional.Either;
import vadintevem.entities.User;
import vadintevem.entities.Message;

import java.util.Collection;

public interface Authors {

    void publish(User user, Message message);

    Either<String, Collection<Message>> findWrittenBy(User user);
}
