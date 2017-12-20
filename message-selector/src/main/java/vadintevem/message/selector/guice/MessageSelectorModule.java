package vadintevem.message.selector.guice;

import com.google.inject.AbstractModule;
import vadintevem.message.selector.MessageSelectorFactory;
import vadintevem.message.selector.impl.DefaultMessageSelectorFactory;

public class MessageSelectorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MessageSelectorFactory.class).to(DefaultMessageSelectorFactory.class);
    }
}
