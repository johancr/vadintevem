package vadintevem.authors;

import vadintevem.base.functional.Either;
import vadintevem.entities.User;
import vadintevem.entities.Message;

import java.util.Collection;
import java.util.Optional;

public interface Authors {

    void publish(User user, Message message);

    Either<String, Collection<Message>> findWrittenBy(User user);

    Optional<User> findAuthorOf(Message message);
}
