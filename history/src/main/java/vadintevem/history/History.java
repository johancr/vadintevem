package vadintevem.history;

import vadintevem.entities.Message;
import vadintevem.entities.User;

import java.util.List;

public interface History {

    List<Message> load(User user);

    void update(Message message, User user);
}
