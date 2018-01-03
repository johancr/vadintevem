package vadintevem.reader.impl.guice;

import com.google.inject.AbstractModule;
import vadintevem.reader.impl.DefaultReaderInteractor;
import vadintevem.reader.ReaderInteractor;

public class ReaderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ReaderInteractor.class).to(DefaultReaderInteractor.class);
    }
}
