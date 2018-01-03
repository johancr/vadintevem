package vadintevem.ranking.stub;

import vadintevem.entities.Message;
import vadintevem.ranking.Ranker;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StubRanker implements Ranker {

    private final Map<Message, Long> ranking;

    @Inject
    public StubRanker() {
        this(new HashMap<Message, Long>() {{
            put(Message.of("Carpe diem").setId(0L), 3L);
            put(Message.of("Le ciel, c'est les autres").setId(1L), 2L);
            put(Message.of("I have always found that plans are useless, but planning is indispensable").setId(2L), 1L);
        }});
    }

    private StubRanker(Map<Message, Long> ranking) {
        this.ranking = ranking;
    }

    public static StubRanker create() {
        return new StubRanker(new HashMap<>());
    }

    @Override
    public void increase(Message message) {
        update(message, 1);
    }

    @Override
    public void decrease(Message message) {
        update(message, -1);
    }

    @Override
    public List<Message> top(int limit) {
        return ranking.entrySet().stream()
                .sorted(comparing((Map.Entry<Message, Long>::getValue)).reversed())
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(toList());
    }

    @Override
    public Optional<Long> findRank(Message message) {
        return Optional.ofNullable(ranking.get(message));
    }

    private void update(Message message, int rankChange) {
        ranking.put(message, ranking.getOrDefault(message, 0L) + rankChange);
    }
}
