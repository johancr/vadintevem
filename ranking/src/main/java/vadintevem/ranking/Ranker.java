package vadintevem.ranking;

import vadintevem.entities.Message;

import java.util.List;
import java.util.Optional;

public interface Ranker {

    void increase(Message message);

    void decrease(Message message);

    List<Message> top(int limit);

    Optional<Long> findRank(Message message);
}
