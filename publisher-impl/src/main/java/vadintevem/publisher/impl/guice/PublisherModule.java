package vadintevem.publisher.impl.guice;

import com.google.inject.AbstractModule;
import vadintevem.publisher.PublisherInteractor;
import vadintevem.publisher.impl.DefaultPublisherInteractor;

public class PublisherModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PublisherInteractor.class).to(DefaultPublisherInteractor.class);
    }
}
