package vadintevem.history;

import vadintevem.entities.Author;
import vadintevem.entities.Message;

import java.util.List;

public interface History {

    List<Message> load();

    List<Message> load(Author author);

    void add(Message message);

    void add(Message message, Author author);
}
