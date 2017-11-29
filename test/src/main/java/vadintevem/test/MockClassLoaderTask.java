package vadintevem.test;

import java.util.ArrayList;
import java.util.Collection;

public abstract class MockClassLoaderTask {

    private Collection<Resource> resources = new ArrayList<>();

    protected void addResource(Resource resource) {
        resources.add(resource);
    }

    public Collection<Resource> getResources() {
        return resources;
    }

    public abstract void configure(MockClassLoader mockClassLoader);

    public abstract void run();
}
