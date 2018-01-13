package vadintevem.history.objectify;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vadintevem.entities.User;
import vadintevem.entities.Message;
import vadintevem.history.History;
import vadintevem.messages.Messages;
import vadintevem.messages.objectify.MessageEntity;
import vadintevem.messages.objectify.ObjectifyMessages;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ObjectifyHistoryTest {

    private final LocalServiceTestHelper dataStoreHelper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    private Closeable session;

    private Messages messages;
    private History history;

    private final User user = User.of("test");

    @Before
    public void setUp() {
        dataStoreHelper.setUp();
        session = ObjectifyService.begin();
        ObjectifyService.register(MessageEntity.class);
        ObjectifyService.register(HistoryEntity.class);

        messages = new ObjectifyMessages();
        history = new ObjectifyHistory();
    }

    @After
    public void tearDown() throws IOException {
        session.close();
        dataStoreHelper.tearDown();
    }

    @Test
    public void load() {
        save(Message.of("some message"), user);

        List<Message> historicMessages = history.load(user);

        assertThat(historicMessages.size(), is(1));
    }

    @Test
    public void saveMultiple() {
        save(Message.of("some message"), user);
        save(Message.of("another message"), user);

        List<Message> historicMessages = history.load(user);

        assertThat(historicMessages.size(), is(2));
    }

    private void save(Message message, User user) {
        Message saved = messages.save(message);
        history.update(saved, user);
    }
}
