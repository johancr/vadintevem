package vadintevem.history.stub;

import vadintevem.entities.Message;
import vadintevem.history.History;

import java.util.*;

public class HistoryStub implements History {

    private static final Set<Message> MESSAGES = new TreeSet<>(Comparator.comparing(Message::getContent));

    @Override
    public List<Message> load() {
        return new ArrayList<>(MESSAGES);
    }

    @Override
    public void add(Message message) {
        MESSAGES.add(message);
    }
}
