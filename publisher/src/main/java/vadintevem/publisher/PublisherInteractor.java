package vadintevem.publisher;

import vadintevem.base.functional.Either;
import vadintevem.base.functional.List;
import vadintevem.entities.Author;
import vadintevem.entities.Message;

import java.util.Collection;


public interface PublisherInteractor {

    Either<List<String>, Void> publish(Message message);

    Either<List<String>, Void> publish(Message message, Author author);

    void increaseRanking(Message message);

    void decreaseRanking(Message message);

    Either<String, Collection<Message>> findWrittenBy(Author author);
}
