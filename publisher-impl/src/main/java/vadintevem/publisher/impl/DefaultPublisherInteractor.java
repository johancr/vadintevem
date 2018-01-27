package vadintevem.publisher.impl;

import vadintevem.authors.Authors;
import vadintevem.base.functional.Either;
import vadintevem.base.functional.List;
import vadintevem.base.functional.Tuple;
import vadintevem.entities.Message;
import vadintevem.entities.User;
import vadintevem.messages.Messages;
import vadintevem.publisher.PublishMessageRequest;
import vadintevem.publisher.PublisherInteractor;
import vadintevem.ranking.Ranker;
import vadintevem.validation.AuthorValidator;
import vadintevem.validation.MessageValidator;

import javax.inject.Inject;
import java.util.Collection;

import static vadintevem.base.functional.Validation.map2;

public class DefaultPublisherInteractor implements PublisherInteractor {

    private final Messages messages;
    private final Authors authors;
    private final Ranker ranker;
    private final MessageValidator messageValidator;
    private final AuthorValidator authorValidator;

    @Inject
    public DefaultPublisherInteractor(Messages messages,
                                      Authors authors,
                                      Ranker ranker,
                                      MessageValidator messageValidator,
                                      AuthorValidator authorValidator) {
        this.messages = messages;
        this.authors = authors;
        this.ranker = ranker;
        this.messageValidator = messageValidator;
        this.authorValidator = authorValidator;
    }

    @Override
    public Either<List<String>, Void> publish(Message message, User user) {
        return map2(messageValidator.validate(message),
                authorValidator.validate(user), Tuple.create())
                .fold(valid -> onSuccess(valid._1, valid._2), Either::left);
    }

    private Either<List<String>, Void> onSuccess(Message message, User user) {
        Message saved = messages.save(message);
        ranker.increase(saved);
        authors.publish(user, saved);
        return Either.right(null);
    }

    @Override
    public Either<List<String>, Void> publish(PublishMessageRequest request) {
        return map2(messageValidator.validate(request.getMessage()),
                authorValidator.validate(request.getUser()), Tuple.create())
                .fold(valid -> onSuccess(request.getPrevious(), valid._1, valid._2), Either::left);
    }

    private Either<List<String>, Void> onSuccess(Message previous, Message message, User user) {
        ranker.decrease(previous);
        return onSuccess(message, user);
    }

    @Override
    public Either<String, Collection<Message>> findWrittenBy(User user) {
        return authors.findWrittenBy(user);
    }
}
