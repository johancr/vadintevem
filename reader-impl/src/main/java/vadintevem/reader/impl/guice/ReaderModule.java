package vadintevem.reader.impl.guice;

import com.google.inject.AbstractModule;
import vadintevem.reader.ReaderInteractor;
import vadintevem.reader.impl.DefaultReaderInteractor;

public class ReaderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ReaderInteractor.class).to(DefaultReaderInteractor.class);
    }
}
