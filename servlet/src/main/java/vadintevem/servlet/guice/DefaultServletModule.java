package vadintevem.servlet.guice;

import com.google.inject.servlet.ServletModule;
import vadintevem.servlet.HelloAppEngine;

public class DefaultServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        serve("/hello").with(HelloAppEngine.class);
    }
}
