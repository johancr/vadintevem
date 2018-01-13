package vadintevem.tracked.messages;

import vadintevem.entities.User;
import vadintevem.entities.Message;

import java.util.Optional;

public interface TrackedMessages {

    Optional<Message> filterFind(User user);
}
