package vadintevem.appengine.stub;

import vadintevem.servlet.SystemProperties;

public class StubbedSystemProperties implements SystemProperties {

    @Override
    public String version() {
        return "Stubbed version";
    }
}
