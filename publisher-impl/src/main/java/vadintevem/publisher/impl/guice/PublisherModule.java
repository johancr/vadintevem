package vadintevem.publisher.impl.guice;

import com.google.inject.AbstractModule;
import vadintevem.publisher.impl.DefaultPublisherInteractor;
import vadintevem.publisher.PublisherInteractor;

public class PublisherModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PublisherInteractor.class).to(DefaultPublisherInteractor.class);
    }
}
