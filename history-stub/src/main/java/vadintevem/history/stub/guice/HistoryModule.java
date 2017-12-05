package vadintevem.history.stub.guice;

import com.google.inject.AbstractModule;
import vadintevem.history.History;
import vadintevem.history.stub.HistoryStub;

public class HistoryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(History.class).to(HistoryStub.class);
    }
}
