package vadintevem.authors.stub.guice;

import com.google.inject.AbstractModule;
import vadintevem.authors.Authors;
import vadintevem.authors.admin.AuthorsAdmin;
import vadintevem.authors.stub.AuthorsStub;

public class StubModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Authors.class).to(AuthorsStub.class);
        bind(AuthorsAdmin.class).to(AuthorsStub.class);
    }
}
