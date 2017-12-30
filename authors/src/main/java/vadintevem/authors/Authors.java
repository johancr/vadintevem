package vadintevem.authors;

import vadintevem.base.functional.Either;
import vadintevem.entities.Author;

import java.util.Collection;

public interface Authors {

    void append(Author author, long messageId);

    Either<String, Collection<Long>> findWrittenBy(Author author);
}
