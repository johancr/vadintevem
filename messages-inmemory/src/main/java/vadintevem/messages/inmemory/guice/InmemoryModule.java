package vadintevem.messages.inmemory.guice;

import com.google.inject.AbstractModule;
import vadintevem.messages.Messages;
import vadintevem.messages.inmemory.InmemoryMessages;

public class InmemoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Messages.class).to(InmemoryMessages.class);
    }
}
