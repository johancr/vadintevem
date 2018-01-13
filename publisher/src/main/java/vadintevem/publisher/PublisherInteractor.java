package vadintevem.publisher;

import vadintevem.base.functional.Either;
import vadintevem.base.functional.List;
import vadintevem.entities.User;
import vadintevem.entities.Message;

import java.util.Collection;


public interface PublisherInteractor {

    Either<List<String>, Void> publish(Message message, User user);

    Either<List<String>, Void> publish(PublishMessageRequest request);

    Either<String, Collection<Message>> findWrittenBy(User user);
}
