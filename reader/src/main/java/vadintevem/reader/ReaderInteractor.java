package vadintevem.reader;

import vadintevem.entities.Author;
import vadintevem.entities.Message;

import java.util.List;
import java.util.Optional;

public interface ReaderInteractor {

    Optional<Message> findMessage();

    Optional<Message> findMessage(Author author);

    Optional<Message> findMessage(String algorithm, Author author);

    Optional<Message> findMessage(FindMessageRequest request);

    Optional<Message> nextMessage(NextMessageRequest request);

    List<Message> loadHistory();
}
