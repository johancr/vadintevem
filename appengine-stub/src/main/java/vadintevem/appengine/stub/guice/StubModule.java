package vadintevem.appengine.stub.guice;

import com.google.inject.AbstractModule;
import vadintevem.appengine.stub.StubbedSystemProperties;
import vadintevem.servlet.SystemProperties;

public class StubModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SystemProperties.class).to(StubbedSystemProperties.class);
    }
}
