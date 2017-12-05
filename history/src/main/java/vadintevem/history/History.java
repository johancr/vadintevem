package vadintevem.history;

import vadintevem.entities.Message;

import java.util.List;

public interface History {

    List<Message> load();

    void add(Message message);
}
