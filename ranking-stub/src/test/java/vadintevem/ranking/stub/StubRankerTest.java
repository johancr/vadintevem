package vadintevem.ranking.stub;

import org.junit.Test;
import vadintevem.entities.Message;
import vadintevem.ranking.Ranker;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StubRankerTest {

    private static Message BAD_MESSAGE = Message.of("bad message").setId(1L);
    private static Message OK_MESSAGE = Message.of("ok message").setId(2L);
    private static Message GOOD_MESSAGE = Message.of("good message").setId(3L);

    @Test
    public void top() {
        Ranker ranker = StubRanker.create();
        increaseRank(BAD_MESSAGE, 1, ranker);
        increaseRank(GOOD_MESSAGE, 5, ranker);
        increaseRank(OK_MESSAGE, 3, ranker);

        List<Message> top = ranker.top(3);

        assertThat(top.get(0), is(GOOD_MESSAGE));
        assertThat(top.get(1), is(OK_MESSAGE));
        assertThat(top.get(2), is(BAD_MESSAGE));
    }

    private void increaseRank(Message message, int rank, Ranker ranker) {
        IntStream.range(0, rank).forEach(i -> ranker.increase(message));
    }
}
