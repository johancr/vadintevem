package vadintevem.reader;

import vadintevem.entities.Message;
import vadintevem.entities.User;

import java.util.List;
import java.util.Optional;

public interface ReaderInteractor {

    Optional<Message> findRandomMessage();

    Optional<Message> findMessage(FindMessageRequest request);

    Optional<Message> nextMessage(NextMessageRequest request);

    List<Message> loadHistory(User user);
}
