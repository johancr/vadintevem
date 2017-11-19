package vadintevem.appengine.mock;

import vadintevem.servlet.SystemProperties;

public class MockedSystemProperties implements SystemProperties {

    @Override
    public String version() {
        return "Mocked version";
    }
}
