package vadintevem.history.objectify;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vadintevem.entities.Author;
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

    private final Author author = Author.of("test");

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
        save(Message.of("some message"), author);

        List<Message> historicMessages = history.load(author);

        assertThat(historicMessages.size(), is(1));
    }

    @Test
    public void saveMultiple() {
        save(Message.of("some message"), author);
        save(Message.of("another message"), author);

        List<Message> historicMessages = history.load(author);

        assertThat(historicMessages.size(), is(2));
    }

    private void save(Message message, Author author) {
        Message saved = messages.save(message);
        history.add(saved, author);
    }
}
