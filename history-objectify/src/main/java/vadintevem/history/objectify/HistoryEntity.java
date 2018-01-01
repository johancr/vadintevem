package vadintevem.history.objectify;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import vadintevem.messages.objectify.MessageEntity;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class HistoryEntity {

    @Id
    private Long id;

    @Index
    private String author;

    private Set<Ref<MessageEntity>> history;

    public HistoryEntity(String author) {
        this();
        this.author = author;
    }

    public HistoryEntity() {
        this.history = new TreeSet<>(Comparator.comparing(ref -> ref.get().getId()));
    }

    public Collection<Ref<MessageEntity>> getHistory() {
        return history;
    }

    public void add(Ref<MessageEntity> message) {
        history.add(message);
    }
}
