package vadintevem.ranking.stub;

import vadintevem.entities.Message;
import vadintevem.ranking.Ranker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StubRanker implements Ranker {

    private final Map<Message, Integer> ranking;

    public StubRanker() {
        this.ranking = new HashMap<>();
    }

    @Override
    public void increase(Message message) {
        ranking.put(message, ranking.getOrDefault(message, 0) + 1);
    }

    @Override
    public List<Message> top(int limit) {
        return ranking.entrySet().stream()
                .sorted(comparing((Map.Entry<Message, Integer>::getValue)).reversed())
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(toList());
    }
}
