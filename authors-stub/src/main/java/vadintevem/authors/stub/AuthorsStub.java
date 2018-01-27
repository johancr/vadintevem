package vadintevem.authors.stub;

import vadintevem.authors.Authors;
import vadintevem.authors.admin.AuthorsAdmin;
import vadintevem.base.functional.Either;
import vadintevem.entities.Message;
import vadintevem.entities.User;

import java.util.*;

public class AuthorsStub implements Authors, AuthorsAdmin {

    private static final Map<User, List<Message>> USERS_MESSAGES = new HashMap<>();

    @Override
    public void publish(User user, Message message) {
        USERS_MESSAGES.computeIfAbsent(user, id -> new ArrayList<>()).add(message);
    }

    @Override
    public Either<String, Collection<Message>> findWrittenBy(User user) {
        return USERS_MESSAGES.containsKey(user)
                ? Either.right(USERS_MESSAGES.get(user))
                : Either.left("User with id " + user.getUsername() + " not found");
    }

    @Override
    public Optional<User> findAuthorOf(Message message) {
        return USERS_MESSAGES.entrySet().stream()
                .filter(entry -> entry.getValue().contains(message))
                .map(Map.Entry::getKey)
                .findAny();
    }

    @Override
    public void deleteAll() {
        USERS_MESSAGES.clear();
    }
}
