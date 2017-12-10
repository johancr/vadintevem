package vadintevem.messages.objectify.guice;

import com.google.inject.AbstractModule;
import vadintevem.messages.Messages;
import vadintevem.messages.objectify.ObjectifyMessages;

public class ObjectifyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Messages.class).to(ObjectifyMessages.class);
    }
}
