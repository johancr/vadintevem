package vadintevem.ranking;

import vadintevem.entities.Message;

import java.util.List;

public interface Ranker {

    void increase(Message message);

    List<Message> top(int n);
}
