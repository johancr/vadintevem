package vadintevem.messages.stub;

import vadintevem.entities.Message;
import vadintevem.messages.Messages;
import vadintevem.messages.admin.MessagesAdmin;

import java.time.Instant;
import java.util.*;

import static vadintevem.entities.Message.of;

public class StubMessages implements Messages, MessagesAdmin {

    private final List<Message> messages;
    private final Random generator;
    private Long sequence;

    public StubMessages() {
        this.sequence = 0L;
        this.generator = new Random(Instant.now().toEpochMilli());
        this.messages = new ArrayList<Message>() {{
            add(of("Carpe diem").setId(nextId()));
            add(of("Le ciel, c'est les autres").setId(nextId()));
            add(of("I have always found that plans are useless, but planning is indispensable").setId(nextId()));
        }};
    }

    private long nextId() {
        return sequence++;
    }

    @Override
    public long save(Message message) {
        Message withId = message.setId(nextId());
        messages.add(withId);
        return withId.getId();
    }

    @Override
    public Optional<Message> find() {
        return Optional.of(messages.get(Math.abs(generator.nextInt()) % messages.size()));
    }

    @Override
    public Collection<Message> findAll() {
        return messages;
    }

    @Override
    public void deleteAll() {
        messages.clear();
    }
}
