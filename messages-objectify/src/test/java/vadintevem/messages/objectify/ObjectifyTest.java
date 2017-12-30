package vadintevem.messages.objectify;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vadintevem.entities.Message;

import java.io.Closeable;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ObjectifyTest {

    private final LocalServiceTestHelper dataStoreHelper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    private Closeable session;

    @Before
    public void setUp() {
        dataStoreHelper.setUp();
        session = ObjectifyService.begin();
    }

    @After
    public void tearDown() throws IOException {
        session.close();
        dataStoreHelper.tearDown();
    }

    @Test
    public void persist() {
        ObjectifyService.register(MessageEntity.class);

        ofy().save().entity(MessageEntity.from(Message.of("some content"))).now();

        MessageEntity loaded = ofy().load().type(MessageEntity.class).id(1L).now();
        assertThat(loaded.getContent(), is("some content"));
    }
}
