package vadintevem.events.websocket.guice;

import com.google.inject.AbstractModule;
import org.eclipse.jetty.websocket.api.Session;
import vadintevem.events.EventNotifier;
import vadintevem.events.websocket.SessionProvider;
import vadintevem.events.websocket.SocketEventNotifier;

public class EventsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Session.class).toProvider(SessionProvider.class);
        bind(EventNotifier.class).to(SocketEventNotifier.class);
    }
}
