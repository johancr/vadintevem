package vadintevem.ranking.stub;

import org.junit.Ignore;
import org.junit.Test;
import vadintevem.entities.Message;
import vadintevem.ranking.Ranker;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class StubRankerTest {

    private static Message BAD_MESSAGE = Message.of("bad message");
    private static Message OK_MESSAGE = Message.of("ok message");
    private static Message GOOD_MESSAGE = Message.of("good message");

    @Test
    @Ignore
    public void top() {
        Ranker ranker = new StubRanker();
        increaseRank(BAD_MESSAGE, 1, ranker);
        increaseRank(GOOD_MESSAGE, 5, ranker);
        increaseRank(OK_MESSAGE, 3, ranker);

        List<Message> top = ranker.top(3);

        assertThat(top.get(2), is(GOOD_MESSAGE));
        assertThat(top.get(1), is(OK_MESSAGE));
        assertThat(top.get(2), is(BAD_MESSAGE));
    }

    private void increaseRank(Message message, int rank, Ranker ranker) {
        IntStream.range(0, rank).forEach(i -> ranker.increase(message));
    }
}
