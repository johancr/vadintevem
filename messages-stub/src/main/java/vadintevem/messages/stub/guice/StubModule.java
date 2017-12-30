package vadintevem.messages.stub.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scope;
import com.google.inject.Singleton;
import vadintevem.messages.Messages;
import vadintevem.messages.stub.StubMessages;

public class StubModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Messages.class).to(StubMessages.class).in(Singleton.class);
    }
}
