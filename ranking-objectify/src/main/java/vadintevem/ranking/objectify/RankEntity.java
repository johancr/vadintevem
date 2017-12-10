package vadintevem.ranking.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import vadintevem.messages.objectify.MessageEntity;

import java.util.HashMap;
import java.util.Map;

@Entity
public class RankEntity {

    @Id
    private Long id;

    private Map<Key<MessageEntity>, Integer> ranking;

    public RankEntity() {
        this.ranking = new HashMap<>();
    }

    public void increase(Key<MessageEntity> message) {
        ranking.put(message, ranking.getOrDefault(message, 0) + 1);
    }

    public Map<Key<MessageEntity>, Integer> getRanking() {
        return ranking;
    }
}
