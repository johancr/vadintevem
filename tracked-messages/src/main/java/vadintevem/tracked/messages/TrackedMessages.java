package vadintevem.tracked.messages;

import vadintevem.entities.Author;
import vadintevem.entities.Message;

import java.util.Optional;

public interface TrackedMessages {

    Optional<Message> find();

    Optional<Message> filterFind(Author author);
}
