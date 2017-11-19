package vadintevem.appengine.mock.guice;

import com.google.inject.AbstractModule;
import vadintevem.appengine.mock.MockedSystemProperties;
import vadintevem.servlet.SystemProperties;

public class MockingModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SystemProperties.class).to(MockedSystemProperties.class);
    }
}
