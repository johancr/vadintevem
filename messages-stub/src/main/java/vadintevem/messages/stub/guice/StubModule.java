package vadintevem.messages.stub.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import vadintevem.messages.Messages;
import vadintevem.messages.admin.MessagesAdmin;
import vadintevem.messages.stub.StubMessages;

public class StubModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StubMessages.class).in(Singleton.class);
        bind(Messages.class).to(StubMessages.class);
        bind(MessagesAdmin.class).to(StubMessages.class);
    }
}
