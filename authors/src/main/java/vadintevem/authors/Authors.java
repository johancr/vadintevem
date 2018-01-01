package vadintevem.authors;

import vadintevem.base.functional.Either;
import vadintevem.entities.Author;
import vadintevem.entities.Message;

import java.util.Collection;

public interface Authors {

    void append(Author author, long messageId);

    Either<String, Collection<Message>> findWrittenBy(Author author);
}
