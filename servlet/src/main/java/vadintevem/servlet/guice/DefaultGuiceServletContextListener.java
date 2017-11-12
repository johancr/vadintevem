package vadintevem.servlet.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import vadintevem.base.GuiceModuleLoader;

public class DefaultGuiceServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(GuiceModuleLoader.load());
    }
}
