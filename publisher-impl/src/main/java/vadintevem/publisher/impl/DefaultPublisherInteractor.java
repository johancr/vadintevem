package vadintevem.publisher.impl;

import vadintevem.entities.Message;
import vadintevem.messages.Messages;
import vadintevem.publisher.PublisherInteractor;

import javax.inject.Inject;

public class DefaultPublisherInteractor implements PublisherInteractor {

    private final Messages messages;

    @Inject
    public DefaultPublisherInteractor(Messages messages) {
        this.messages = messages;
    }

    @Override
    public void publish(Message message) {
        messages.save(message);
    }
}
