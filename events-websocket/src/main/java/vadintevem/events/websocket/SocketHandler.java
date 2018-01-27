package vadintevem.events.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class SocketHandler implements WebSocketListener {

    private static final Map<String, Session> SESSIONS = new HashMap<>();

    private final ObjectReader objectReader = new ObjectMapper().readerFor(EventDto.class);
    private final String username;

    public SocketHandler(String username) {
        this.username = username;
    }

    @Override
    public void onWebSocketClose(int i, String s) {
        SESSIONS.remove(username);
    }

    @Override
    public void onWebSocketConnect(Session session) {
        SESSIONS.put(username, session);
    }

    @Override
    public void onWebSocketError(Throwable throwable) {
        SESSIONS.remove(username);
    }

    @Override
    public void onWebSocketBinary(byte[] bytes, int i, int i1) {

    }

    @Override
    public void onWebSocketText(String message) {
        EventDto event = parse(message);
        Optional.ofNullable(SESSIONS.get(event.getUsername()))
                .ifPresent(send(message));
    }

    private EventDto parse(String message) {
        try
        {
            return objectReader.readValue(message);
        }
        catch (IOException ex)
        {
            throw new IllegalStateException(ex);
        }
    }

    private Consumer<Session> send(String message) {
        return session -> {
            try
            {
                session.getRemote().sendString(message);
            }
            catch (IOException ex)
            {
                throw new IllegalStateException(ex);
            }
        };
    }
}
