package vadintevem.explorer.impl.guice;

import com.google.inject.AbstractModule;
import vadintevem.explorer.ExplorerInteractor;
import vadintevem.explorer.impl.ExplorerInteractorImpl;

public class ExplorerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ExplorerInteractor.class).to(ExplorerInteractorImpl.class);
    }
}
