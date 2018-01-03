package vadintevem.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ServiceLoader;

public class MultiServiceLoader {

    public static <S> Collection<S> load(Class<S> serviceClass) {
        Collection<S> resources = new ArrayList<>();
        for (S resource : ServiceLoader.load(serviceClass))
        {
            resources.add(resource);
        }
        return resources;
    }
}
