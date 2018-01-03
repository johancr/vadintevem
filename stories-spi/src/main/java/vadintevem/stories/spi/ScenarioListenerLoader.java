package vadintevem.stories.spi;

import vadintevem.base.MultiServiceLoader;

import java.util.Collection;

public class ScenarioListenerLoader {

    public static Collection<ScenarioListener> load() {
        return MultiServiceLoader.load(ScenarioListener.class);
    }
}
