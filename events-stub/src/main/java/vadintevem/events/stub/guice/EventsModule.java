package vadintevem.events.stub.guice;

import com.google.inject.AbstractModule;
import vadintevem.events.EventNotifier;
import vadintevem.events.stub.EventNotifierStub;

public class EventsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventNotifier.class).to(EventNotifierStub.class);
    }
}
