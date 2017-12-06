package vadintevem.messages.objectify;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vadintevem.entities.Message;
import vadintevem.messages.Messages;

import java.io.Closeable;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ObjectifyMessagesTest {

    private final LocalServiceTestHelper dataStoreHelper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    private Closeable session;

    @Before
    public void setUp() {
        dataStoreHelper.setUp();
        session = ObjectifyService.begin();
        ObjectifyService.register(MessageEntity.class);
    }

    @After
    public void tearDown() throws IOException {
        session.close();
        dataStoreHelper.tearDown();
    }

    @Test
    public void save() {
        Messages messages = new ObjectifyMessages();

        messages.save(Message.of("some content"));

        assertThat(messages.find().map(Message::getContent).orElse("not found"), is(not("not found")));
    }
}
