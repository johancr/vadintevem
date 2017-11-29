package vadintevem.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ServiceLoader;

public class ContextListenerLoader {

    public static Collection<ContextListener> load() {
        Collection<ContextListener> resources = new ArrayList<>();
        for (ContextListener resource : ServiceLoader.load(ContextListener.class))
        {
            resources.add(resource);
        }
        return resources;
    }

    public static void init(Collection<ContextListener> contextListeners) {
        contextListeners.forEach(ContextListener::init);
    }
}
