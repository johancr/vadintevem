package vadintevem.servlet.objectify.guice;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;

import javax.inject.Singleton;

public class ObjectifyModule extends AbstractModule {

    @Override
    protected void configure() {
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
