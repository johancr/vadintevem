package vadintevem.appengine.stub;

import vadintevem.events.remote.RemoteEventConfiguration;

public class StubbedRemoteEventConfiguration implements RemoteEventConfiguration {

    @Override
    public String getHost() {
        return "http://localhost:8080";
    }
}
