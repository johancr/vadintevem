package vadintevem.stories.objectify;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import vadintevem.stories.spi.ScenarioListener;

import java.io.Closeable;
import java.io.IOException;

import static vadintevem.base.ContextListenerLoader.init;
import static vadintevem.base.ContextListenerLoader.load;

public class ObjectifyScenarioListener implements ScenarioListener {

    private final LocalServiceTestHelper dataStoreHelper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    private Closeable session;

    @Override
    public void onStart() {
        dataStoreHelper.setUp();
        session = ObjectifyService.begin();
        init(load());
    }

    @Override
    public void onEnd() {
        try
        {
            session.close();
        }
        catch (IOException ex)
        {
            throw new IllegalStateException("Failed to close session", ex);
        }
        dataStoreHelper.tearDown();
    }
}
