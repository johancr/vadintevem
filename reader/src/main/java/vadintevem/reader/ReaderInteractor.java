package vadintevem.reader;

import vadintevem.entities.Message;

import java.util.Optional;

public interface ReaderInteractor {

    Optional<Message> findMessage();
}
