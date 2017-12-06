package vadintevem.messages.stub;

import vadintevem.entities.Message;
import vadintevem.messages.Messages;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class StubMessages implements Messages {

    private static final List<String> MESSAGES = new ArrayList<String>() {{
        add("Carpe diem");
        add("Le ciel, c'est les autres");
        add("I have always found that plans are useless, but planning is indispensable");
    }};

    private final Random generator = new Random(Instant.now().toEpochMilli());

    @Override
    public void save(Message message) {
        MESSAGES.add(message.getContent());
    }

    @Override
    public Optional<Message> find() {
        return Optional.of(Message.of(MESSAGES.get(Math.abs(generator.nextInt()) % MESSAGES.size())));
    }
}
