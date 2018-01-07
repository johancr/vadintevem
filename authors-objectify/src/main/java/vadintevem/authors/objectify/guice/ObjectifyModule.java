package vadintevem.authors.objectify.guice;

import com.google.inject.AbstractModule;
import vadintevem.authors.Authors;
import vadintevem.authors.admin.AuthorsAdmin;
import vadintevem.authors.objectify.ObjectifyAuthors;

public class ObjectifyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Authors.class).to(ObjectifyAuthors.class);
        bind(AuthorsAdmin.class).to(ObjectifyAuthors.class);
    }
}
