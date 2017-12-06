package vadintevem.history.objectify;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import vadintevem.messages.objectify.MessageEntity;

import java.util.*;

@Entity
public class HistoryEntity {

    @Id
    private Long id;

    private Set<Ref<MessageEntity>> history;

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
