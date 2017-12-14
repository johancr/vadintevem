package vadintevem.publisher.impl;

import vadintevem.base.functional.Either;
import vadintevem.base.functional.List;
import vadintevem.base.functional.Validation;
import vadintevem.entities.Message;
import vadintevem.messages.Messages;
import vadintevem.publisher.PublisherInteractor;
import vadintevem.ranking.Ranker;
import vadintevem.validation.MessageValidator;

import javax.inject.Inject;

import static vadintevem.base.functional.Either.right;

public class DefaultPublisherInteractor implements PublisherInteractor {

    private final Messages messages;
    private final Ranker ranker;
    private final MessageValidator validator;

    @Inject
    public DefaultPublisherInteractor(Messages messages,
                                      Ranker ranker,
                                      MessageValidator validator) {
        this.messages = messages;
        this.ranker = ranker;
        this.validator = validator;
    }

    @Override
    public Either<List<String>, Void> publish(Message message) {
        return validator.validate(message).fold(this::onSuccess, Either::left);
    }

    private Either<List<String>, Void> onSuccess(Message message) {
        messages.save(message);
        return Either.right(null);
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
