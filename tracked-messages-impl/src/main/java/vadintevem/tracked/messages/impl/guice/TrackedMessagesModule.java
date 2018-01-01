package vadintevem.tracked.messages.impl.guice;

import com.google.inject.AbstractModule;
import vadintevem.tracked.messages.TrackedMessages;
import vadintevem.tracked.messages.impl.DefaultTrackedMessages;

public class TrackedMessagesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TrackedMessages.class).to(DefaultTrackedMessages.class);
    }
}
