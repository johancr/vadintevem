package vadintevem.publisher.impl;

import vadintevem.entities.Message;
import vadintevem.messages.Messages;
import vadintevem.publisher.PublisherInteractor;
import vadintevem.ranking.Ranker;

import javax.inject.Inject;

public class DefaultPublisherInteractor implements PublisherInteractor {

    private final Messages messages;
    private final Ranker ranker;

    @Inject
    public DefaultPublisherInteractor(Messages messages, Ranker ranker) {
        this.messages = messages;
        this.ranker = ranker;
    }

    @Override
    public void publish(Message message) {
        messages.save(message);
    }

    @Override
    public void increaseRanking(Message message) {
        ranker.increase(message);
    }

    @Override
    public void decreaseRanking(Message message) {
        ranker.decrease(message);
    }
}
