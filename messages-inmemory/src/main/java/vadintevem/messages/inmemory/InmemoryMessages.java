package vadintevem.messages.inmemory;

import vadintevem.entities.Message;
import vadintevem.messages.Messages;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InmemoryMessages implements Messages {

    private static final List<String> MESSAGES = Arrays.asList(
            "Carpe diem",
            "Le ciel, c'est les autres",
            "I have always found that plans are useless, but planning is indispensable"
    );

    private final Random generator = new Random(Instant.now().toEpochMilli());

    @Override
    public Message next() {
        return new Message() {
            @Override
            public String getContent() {
                return MESSAGES.get(Math.abs(generator.nextInt()) % 3);
            }
        };
    }
}
