package vadintevem.history.stub;

import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.messages.Messages;

import javax.inject.Inject;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class HistoryStub implements History {

    private static final Map<Author, List<Long>> MESSAGES = new HashMap<>();
    private final Messages messages;

    @Inject
    public HistoryStub(Messages messages) {
        this.messages = messages;
    }

    @Override
    public List<Message> load() {
        return load(Author.of("unknown"));
    }

    @Override
    public List<Message> load(Author author) {
        return messages.findAll().stream()
                .filter(message -> inHistory(message.getId(), author))
                .collect(toList());
    }

    @Override
    public void add(Message message) {
        add(message, Author.of("unknown"));
    }

    @Override
    public void add(Message message, Author author) {
        MESSAGES.compute(author, (x, y) -> new ArrayList<>()).add(message.getId());
    }

    private static boolean inHistory(Long messageId, Author author) {
        return MESSAGES.getOrDefault(author, Collections.emptyList()).contains(messageId);
    }
}
