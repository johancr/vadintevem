package vadintevem.history;

import vadintevem.entities.Author;
import vadintevem.entities.Message;

import java.util.List;

public interface History {

    List<Message> load(Author author);

    void add(Message message, Author author);
}
