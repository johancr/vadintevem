package vadintevem.publisher;

import vadintevem.base.functional.Either;
import vadintevem.base.functional.List;
import vadintevem.entities.Message;


public interface PublisherInteractor {

    Either<List<String>, Void> publish(Message message);

    void increaseRanking(Message message);

    void decreaseRanking(Message message);
}
