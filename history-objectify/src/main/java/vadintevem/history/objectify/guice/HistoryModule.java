package vadintevem.history.objectify.guice;

import com.google.inject.AbstractModule;
import vadintevem.history.History;
import vadintevem.history.objectify.ObjectifyHistory;

public class HistoryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(History.class).to(ObjectifyHistory.class);
    }
}
