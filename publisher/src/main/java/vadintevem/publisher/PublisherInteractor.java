package vadintevem.publisher;

import vadintevem.entities.Message;

public interface PublisherInteractor {

    void publish(Message message);

    void increaseRanking(Message message);
}
