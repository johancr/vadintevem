package vadintevem.ranking.objectify;

import com.googlecode.objectify.Key;
import vadintevem.base.functional.Effect;
import vadintevem.entities.Message;
import vadintevem.messages.objectify.MessageEntity;
import vadintevem.ranking.Ranker;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class ObjectifyRanker implements Ranker {

    @Override
    public void increase(Message message) {
        update(message, rank -> rank::increase);
    }

    @Override
    public void decrease(Message message) {
        update(message, rank -> rank::decrease);
    }

    @Override
    public List<Message> top(int limit) {
        return ofy().load().type(RankEntity.class).first().now().getRanking().entrySet().stream()
                .sorted(comparing((Map.Entry<Key<MessageEntity>, Integer>::getValue)).reversed())
                .limit(limit)
                .map(Map.Entry::getKey)
                .map(key -> ofy().load().key(key).now())
                .collect(toList());
    }

    private void update(Message message, Function<RankEntity, Effect<Key<MessageEntity>>> rankChange) {
        RankEntity rank = getRankEntity();
        Key<MessageEntity> messageEntity = Key.create(MessageEntity.class, message.getId());
        rankChange.apply(rank).apply(messageEntity);
        ofy().save().entity(rank).now();
    }

    private RankEntity getRankEntity() {
        RankEntity entity = ofy().load().type(RankEntity.class).first().now();
        return entity != null
                ? entity
                : new RankEntity();
    }
}
