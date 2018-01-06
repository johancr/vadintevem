package vadintevem.reader;

import vadintevem.entities.Author;
import vadintevem.entities.Message;

import java.util.List;
import java.util.Optional;

public interface ReaderInteractor {

    Optional<Message> findRandomMessage();

    Optional<Message> findMessage(FindMessageRequest request);

    Optional<Message> nextMessage(NextMessageRequest request);

    List<Message> loadHistory(Author author);
}
