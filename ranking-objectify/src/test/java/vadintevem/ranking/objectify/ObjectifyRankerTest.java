package vadintevem.ranking.objectify;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vadintevem.entities.Message;
import vadintevem.messages.Messages;
import vadintevem.messages.objectify.MessageEntity;
import vadintevem.messages.objectify.ObjectifyMessages;
import vadintevem.ranking.Ranker;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ObjectifyRankerTest {

    private static Message BAD_MESSAGE = Message.of("bad message");
    private static Message OK_MESSAGE = Message.of("ok message");
    private static Message GOOD_MESSAGE = Message.of("good message");

    private final LocalServiceTestHelper dataStoreHelper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private Closeable session;

    @Before
    public void setUp() {
        dataStoreHelper.setUp();
        session = ObjectifyService.begin();
        ObjectifyService.register(MessageEntity.class);
        ObjectifyService.register(RankEntity.class);
    }

    @After
    public void tearDown() throws IOException {
        session.close();
        dataStoreHelper.tearDown();
    }

    @Test
    public void increase() {
        Messages messages = new ObjectifyMessages();
        Ranker ranker = new ObjectifyRanker();
        increaseRank(BAD_MESSAGE, 1, messages, ranker);
        increaseRank(GOOD_MESSAGE, 5, messages, ranker);
        increaseRank(OK_MESSAGE, 3, messages, ranker);

        List<Message> top = ranker.top(3);

        assertThat(top.get(0).getContent(), is(GOOD_MESSAGE.getContent()));
        assertThat(top.get(1).getContent(), is(OK_MESSAGE.getContent()));
        assertThat(top.get(2).getContent(), is(BAD_MESSAGE.getContent()));
    }

    private void increaseRank(Message message, int rank, Messages messages, Ranker ranker) {
        messages.save(message);
        IntStream.range(0, rank).forEach(i -> ranker.increase(message));
    }
}
