package vadintevem.servlet.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import vadintevem.base.ContextListenerLoader;
import vadintevem.base.GuiceModuleLoader;

import javax.servlet.ServletContextEvent;

public class DefaultGuiceServletContextListener extends GuiceServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        super.contextInitialized(servletContextEvent);
        ContextListenerLoader.init(ContextListenerLoader.load());
    }

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                JerseyServletModuleFactory.create(),
                GuiceModuleLoader.load());
    }
}
