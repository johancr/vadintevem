package vadintevem.authors.objectify;

import com.googlecode.objectify.ObjectifyService;
import vadintevem.base.ContextListener;

public class ObjectifyContextListener implements ContextListener {

    @Override
    public void init() {
        ObjectifyService.register(AuthorEntity.class);
    }
}

