package vadintevem.appengine;

import vadintevem.events.remote.RemoteEventConfiguration;

public class AppEngineRemoteEventConfiguration implements RemoteEventConfiguration {

    @Override
    public String getHost() {
        return "http://gce-events-instance.c.vadintevem.internal";
    }
}
