package vadintevem.ranking.stub;

import vadintevem.entities.Message;
import vadintevem.ranking.Ranker;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StubRanker implements Ranker {

    private final Map<Message, Integer> ranking;

    @Inject
    public StubRanker() {
        this(new HashMap<Message, Integer>() {{
            put(Message.of("Carpe diem"), 3);
            put(Message.of("Le ciel, c'est les autres"), 2);
            put(Message.of("I have always found that plans are useless, but planning is indispensable"), 1);
        }});
    }

    private StubRanker(Map<Message, Integer> ranking) {
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
                .sorted(comparing((Map.Entry<Message, Integer>::getValue)).reversed())
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(toList());
    }

    private void update(Message message, int rankChange) {
        ranking.put(message, ranking.getOrDefault(message, 0) + rankChange);
    }
}
