package vadintevem.ranking.objectify.guice;

import com.google.inject.AbstractModule;
import vadintevem.ranking.Ranker;
import vadintevem.ranking.objectify.ObjectifyRanker;

public class ObjectifyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Ranker.class).to(ObjectifyRanker.class);
    }
}
