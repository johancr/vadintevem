package vadintevem.reader.impl;

import vadintevem.entities.Author;
import vadintevem.entities.Message;

import java.util.List;
import java.util.Optional;

public interface ReaderInteractor {

    Optional<Message> findMessage();

    Optional<Message> findMessage(String algorithm, Author author);

    Optional<Message> findMessage(Author author);

    Optional<Message> nextMessage(Message previous);

    Optional<Message> nextMessage(Message previous, Author author);

    List<Message> loadHistory();

    void saveHistory(Message message);
}
