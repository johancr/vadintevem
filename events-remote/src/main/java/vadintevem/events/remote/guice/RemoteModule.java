package vadintevem.events.remote.guice;

import com.google.inject.AbstractModule;
import vadintevem.events.EventNotifier;
import vadintevem.events.remote.RemoteEventNotifier;

public class RemoteModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventNotifier.class).to(RemoteEventNotifier.class);
    }
}
