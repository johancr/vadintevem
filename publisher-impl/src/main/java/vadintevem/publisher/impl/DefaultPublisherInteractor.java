package vadintevem.publisher.impl;

import vadintevem.authors.Authors;
import vadintevem.base.functional.Either;
import vadintevem.base.functional.List;
import vadintevem.base.functional.Tuple;
import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.messages.Messages;
import vadintevem.publisher.PublisherInteractor;
import vadintevem.ranking.Ranker;
import vadintevem.validation.AuthorValidator;
import vadintevem.validation.MessageValidator;

import javax.inject.Inject;

import java.util.Collection;

import static java.util.stream.Collectors.toList;
import static vadintevem.base.functional.Validation.map2;

public class DefaultPublisherInteractor implements PublisherInteractor {

    private final Messages messages;
    private final Authors authors;
    private final Ranker ranker;
    private final MessageValidator messageValidator;
    private final AuthorValidator authorValidator;

    @Inject
    public DefaultPublisherInteractor(Messages messages,
                                      Authors authors, Ranker ranker,
                                      MessageValidator messageValidator,
                                      AuthorValidator authorValidator) {
        this.messages = messages;
        this.authors = authors;
        this.ranker = ranker;
        this.messageValidator = messageValidator;
        this.authorValidator = authorValidator;
    }

    @Override
    public Either<List<String>, Void> publish(Message message) {
        return messageValidator.validate(message).fold(this::onSuccess, Either::left);
    }

    private Either<List<String>, Void> onSuccess(Message message) {
        messages.save(message);
        return Either.right(null);
    }

    @Override
    public Either<List<String>, Void> publish(Message message, Author author) {
        return map2(messageValidator.validate(message),
                authorValidator.validate(author), Tuple.create())
                .fold(valid -> onSuccess(valid._1, valid._2), Either::left);
    }

    private Either<List<String>, Void> onSuccess(Message message, Author author) {
        Message saved = messages.save(message);
        ranker.increase(saved);
        authors.append(author, saved.getId());
        return Either.right(null);
    }

    @Override
    public Either<String, Collection<Message>> findWrittenBy(Author author) {
        return authors.findWrittenBy(author);
    }
}
