package vadintevem.objectify.guice;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;
import vadintevem.messages.Messages;
import vadintevem.objectify.ObjectifyMessages;

import javax.inject.Singleton;

public class ObjectifyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Messages.class).to(ObjectifyMessages.class);
        bind(ObjectifyFilter.class).in(Singleton.class);
        install(new ObjectifyServletModule());
    }

    private static class ObjectifyServletModule extends ServletModule {

        @Override
        protected void configureServlets() {
            filter("/*").through(ObjectifyFilter.class);
        }
    }
}
