package vadintevem.gce.events;

import vadintevem.events.websocket.WebSocketEventConfiguration;

public class GceWebSocketEventConfiguration implements WebSocketEventConfiguration {

    @Override
    public String getHost() {
        return "ws://localhost";
    }
}
