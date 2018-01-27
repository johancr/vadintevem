package vadintevem.events.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import javax.inject.Provider;
import java.net.URI;
import java.util.concurrent.Future;

public class SessionProvider implements Provider<Session> {

    private static final String BACKEND_USERNAME = "BACKEND_USERNAME";

    @Override
    public Session get() {
        WebSocketClient client = new WebSocketClient();

        try
        {
            client.start();
            URI uri = new URI("ws://localhost:8080/ws/events?username=" + BACKEND_USERNAME);
            Future<Session> session = client.connect(new EventPublishingWebSocket(), uri);
            return session.get();
        }
        catch (Exception ex)
        {
            throw new IllegalStateException("Could not start web socket client", ex);
        }
    }

    @WebSocket
    public static class EventPublishingWebSocket implements WebSocketListener {

        @Override
        public void onWebSocketBinary(byte[] bytes, int i, int i1) {

        }

        @Override
        public void onWebSocketText(String s) {

        }

        @Override
        public void onWebSocketClose(int i, String s) {

        }

        @Override
        public void onWebSocketConnect(Session session) {

        }

        @Override
        public void onWebSocketError(Throwable throwable) {

        }
    }
}
