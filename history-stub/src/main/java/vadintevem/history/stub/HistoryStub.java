package vadintevem.history.stub;

import vadintevem.entities.Message;
import vadintevem.entities.User;
import vadintevem.history.History;
import vadintevem.messages.Messages;

import javax.inject.Inject;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class HistoryStub implements History {

    private static final Map<User, Set<Message>> MESSAGES = new HashMap<>();
    private final Messages messages;

    @Inject
    public HistoryStub(Messages messages) {
        this.messages = messages;
    }

    @Override
    public List<Message> load(User user) {
        return messages.findAll().stream()
                .filter(message -> inHistory(message, user))
                .collect(toList());
    }

    @Override
    public void update(Message message, User user) {
        Set<Message> history = MESSAGES.getOrDefault(user, new HashSet<>());
        history.add(message);
        MESSAGES.putIfAbsent(user, history);
    }

    private static boolean inHistory(Message message, User user) {
        return MESSAGES.getOrDefault(user, new HashSet<>()).contains(message);
    }
}
