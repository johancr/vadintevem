package vadintevem.appengine.guice;

import com.google.inject.AbstractModule;
import vadintevem.appengine.AppEngineRemoteEventConfiguration;
import vadintevem.appengine.AppEngineSystemProperties;
import vadintevem.events.remote.RemoteEventConfiguration;
import vadintevem.servlet.SystemProperties;

public class AppEngineModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SystemProperties.class).to(AppEngineSystemProperties.class);
        bind(RemoteEventConfiguration.class).to(AppEngineRemoteEventConfiguration.class);
    }
}
