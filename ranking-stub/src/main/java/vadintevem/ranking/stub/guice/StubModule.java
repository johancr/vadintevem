package vadintevem.ranking.stub.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import vadintevem.ranking.Ranker;
import vadintevem.ranking.stub.StubRanker;

public class StubModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Ranker.class).to(StubRanker.class).in(Singleton.class);
    }
}
