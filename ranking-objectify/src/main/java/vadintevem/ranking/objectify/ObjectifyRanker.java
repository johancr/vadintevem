package vadintevem.ranking.objectify;

import com.googlecode.objectify.Key;
import vadintevem.base.functional.Effect;
import vadintevem.entities.Message;
import vadintevem.messages.objectify.MessageEntity;
import vadintevem.ranking.Ranker;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        RankEntity entity = ofy().load().type(RankEntity.class).first().now();

        if (entity != null) {
            return entity.getRanking().entrySet().stream()
                .sorted(comparing((Map.Entry<Key<MessageEntity>, Long>::getValue)).reversed())
                .limit(limit)
                .map(Map.Entry::getKey)
                .map(key -> ofy().load().key(key).now())
                .map(MessageEntity::toDomain)
                .collect(toList());
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<Long> findRank(Message message) {
        return Optional.ofNullable(getRankEntity().getRanking().get(Key.create(MessageEntity.class, message.getId())));
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
