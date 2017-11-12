package vadintevem.appengine;

import com.google.appengine.api.utils.SystemProperty;
import vadintevem.servlet.SystemProperties;

public class AppEngineSystemProperties implements SystemProperties {

    @Override
    public String version() {
        return SystemProperty.version.get();
    }
}
