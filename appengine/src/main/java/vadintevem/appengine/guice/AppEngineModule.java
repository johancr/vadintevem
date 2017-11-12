package vadintevem.appengine.guice;

import com.google.inject.AbstractModule;
import vadintevem.appengine.AppEngineSystemProperties;
import vadintevem.servlet.SystemProperties;

public class AppEngineModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SystemProperties.class).to(AppEngineSystemProperties.class);
    }
}
