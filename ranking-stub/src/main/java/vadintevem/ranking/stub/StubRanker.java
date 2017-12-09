package vadintevem.ranking.stub;

import vadintevem.entities.Message;
import vadintevem.ranking.Ranker;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Message> top(int n) {
        return ranking.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .limit(n)
                .collect(toList());
    }
}
