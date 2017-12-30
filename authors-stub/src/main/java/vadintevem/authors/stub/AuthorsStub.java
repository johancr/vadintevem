package vadintevem.authors.stub;

import vadintevem.authors.Authors;
import vadintevem.base.functional.Either;
import vadintevem.entities.Author;
import vadintevem.entities.Message;

import java.util.*;

public class AuthorsStub implements Authors {

    private static final Map<String, List<Long>> AUTHORS_MESSAGES = new HashMap<>();

    @Override
    public void append(Author author, long messageId) {
        AUTHORS_MESSAGES.computeIfAbsent(author.getId(), id -> new ArrayList<>()).add(messageId);
    }

    @Override
    public Either<String, Collection<Long>> findWrittenBy(Author author) {
        return AUTHORS_MESSAGES.containsKey(author.getId())
                ? Either.right(AUTHORS_MESSAGES.get(author.getId()))
                : Either.left("Author with id " + author.getId() + " not found");
    }
}
