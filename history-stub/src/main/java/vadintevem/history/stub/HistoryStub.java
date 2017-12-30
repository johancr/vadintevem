package vadintevem.history.stub;

import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.messages.Messages;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class HistoryStub implements History {

    private static final List<Long> MESSAGES = new ArrayList<>();
    private final Messages messages;

    @Inject
    public HistoryStub(Messages messages) {
        this.messages = messages;
    }

    @Override
    public List<Message> load() {
        return messages.findAll().stream()
                .filter(message -> MESSAGES.contains(message.getId()))
                .collect(toList());
    }

    @Override
    public void add(Message message) {
        MESSAGES.add(message.getId());
    }
}
