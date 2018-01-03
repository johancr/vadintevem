package vadintevem.base;

import java.util.Collection;

public class ContextListenerLoader {

    public static Collection<ContextListener> load() {
        return MultiServiceLoader.load(ContextListener.class);
    }

    public static void init(Collection<ContextListener> contextListeners) {
        contextListeners.forEach(ContextListener::init);
    }
}
