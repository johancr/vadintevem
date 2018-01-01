package vadintevem.message.selector.impl;

import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.message.selector.MessageSelector;
import vadintevem.messages.Messages;

import javax.inject.Inject;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class RandomMessageSelector implements MessageSelector {

    private final Messages messages;
    private final Random generator = new Random(Instant.now().toEpochMilli());

    @Inject
    public RandomMessageSelector(Messages messages) {
        this.messages = messages;
    }

    @Override
    public Optional<Message> select() {
        List<Message> all = new ArrayList<>(messages.findAll());
        return all.size() > 0
                ? Optional.of(random(all))
                : Optional.empty();
    }

    @Override
    public Optional<Message> selectBasedOn(Author author) {
        return select();
    }

    private Message random(List<Message> popular) {
        return popular.get(Math.abs(generator.nextInt()) % popular.size());
    }
}
