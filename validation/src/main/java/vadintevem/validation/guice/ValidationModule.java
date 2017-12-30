package vadintevem.validation.guice;

import com.google.inject.AbstractModule;
import vadintevem.validation.AuthorValidator;
import vadintevem.validation.DefaultAuthorValidator;
import vadintevem.validation.DefaultMessageValidator;
import vadintevem.validation.MessageValidator;

public class ValidationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MessageValidator.class).to(DefaultMessageValidator.class);
        bind(AuthorValidator.class).to(DefaultAuthorValidator.class);
    }
}
