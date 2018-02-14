package vadintevem.gce.events.guice;

import com.google.inject.AbstractModule;
import vadintevem.events.websocket.WebSocketEventConfiguration;
import vadintevem.gce.events.GceWebSocketEventConfiguration;

public class GceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WebSocketEventConfiguration.class).to(GceWebSocketEventConfiguration.class);
    }
}
