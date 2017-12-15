package vadintevem.tracked.messages.stub.guice;

import com.google.inject.AbstractModule;
import vadintevem.tracked.messages.TrackedMessages;
import vadintevem.tracked.messages.stub.TrackedMessagesStub;

public class StubModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TrackedMessages.class).to(TrackedMessagesStub.class);
    }
}
