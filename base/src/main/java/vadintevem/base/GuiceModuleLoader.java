package vadintevem.base;

import com.google.inject.Module;
import com.google.inject.util.Modules;

import java.util.ServiceLoader;

public class GuiceModuleLoader {

    public static Module load() {
        return Modules.combine(ServiceLoader.load(Module.class));
    }

}
