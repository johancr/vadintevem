package vadintevem.message.selector.impl;

import vadintevem.entities.Message;
import vadintevem.message.selector.MessageSelector;
import vadintevem.ranking.Ranker;

import javax.inject.Inject;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class PopularMessageSelector implements MessageSelector {

    private final Ranker ranker;
    private final Random generator = new Random(Instant.now().toEpochMilli());

    @Inject
    public PopularMessageSelector(Ranker ranker) {
        this.ranker = ranker;
    }

    @Override
    public Optional<Message> select() {
        List<Message> popular = ranker.top(5);
        return popular.size() > 0
                ? Optional.of(random(popular))
                : Optional.empty();
    }

    private Message random(List<Message> popular) {
        return popular.get(Math.abs(generator.nextInt()) % popular.size());
    }
}
