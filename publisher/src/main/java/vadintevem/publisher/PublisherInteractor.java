package vadintevem.publisher;

import vadintevem.base.functional.Either;
import vadintevem.base.functional.List;
import vadintevem.entities.Author;
import vadintevem.entities.Message;

import java.util.Collection;


public interface PublisherInteractor {

    Either<List<String>, Void> publish(Message message, Author author);

    Either<List<String>, Void> publish(PublishMessageRequest request);

    Either<String, Collection<Message>> findWrittenBy(Author author);
}
