package vadintevem.authors.stub;

import vadintevem.authors.Authors;
import vadintevem.authors.admin.AuthorsAdmin;
import vadintevem.base.functional.Either;
import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.messages.Messages;

import javax.inject.Inject;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class AuthorsStub implements Authors, AuthorsAdmin {

    private static final Map<String, List<Long>> AUTHORS_MESSAGES = new HashMap<>();

    private final Messages messages;

    @Inject
    public AuthorsStub(Messages messages) {
        this.messages = messages;
    }

    @Override
    public void append(Author author, long messageId) {
        AUTHORS_MESSAGES.computeIfAbsent(author.getId(), id -> new ArrayList<>()).add(messageId);
    }

    @Override
    public Either<String, Collection<Message>> findWrittenBy(Author author) {
        return AUTHORS_MESSAGES.containsKey(author.getId())
                ? Either.right(toMessages(AUTHORS_MESSAGES.get(author.getId())))
                : Either.left("Author with id " + author.getId() + " not found");
    }

    private Collection<Message> toMessages(Collection<Long> messageIds) {
        return messages.findAll().stream()
                .filter(message -> messageIds.contains(message.getId())).collect(toList());
    }

    @Override
    public void deleteAll() {
        AUTHORS_MESSAGES.clear();
    }
}
