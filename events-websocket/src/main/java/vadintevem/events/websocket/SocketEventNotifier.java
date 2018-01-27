package vadintevem.events.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.eclipse.jetty.websocket.api.Session;
import vadintevem.events.Event;
import vadintevem.events.EventNotifier;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;

public class SocketEventNotifier implements EventNotifier {

    private final ObjectWriter objectWriter = new ObjectMapper().writerFor(EventDto.class);
    private final Provider<Session> sessionProvider;

    @Inject
    public SocketEventNotifier(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    @Override
    public void notify(Event event) {
        send(serialize(event));
    }

    private String serialize(Event event) {
        try
        {
            return objectWriter.writeValueAsString(EventDto.from(event));
        }
        catch (JsonProcessingException ex)
        {
            throw new IllegalStateException(ex);
        }
    }

    private void send(String event) {
        try
        {
            session().getRemote().sendString(event);
        }
        catch (IOException ex)
        {
            throw new IllegalStateException(ex);
        }
    }

    private Session session() {
        return sessionProvider.get();
    }
}
