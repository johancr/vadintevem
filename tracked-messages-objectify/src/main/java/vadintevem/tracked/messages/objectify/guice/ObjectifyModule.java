package vadintevem.tracked.messages.objectify.guice;

import com.google.inject.AbstractModule;
import vadintevem.tracked.messages.TrackedMessages;
import vadintevem.tracked.messages.objectify.ObjectifyTrackedMessages;

public class ObjectifyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TrackedMessages.class).to(ObjectifyTrackedMessages.class);
    }
}
