package vadintevem.events.stub;

import vadintevem.events.Event;
import vadintevem.events.EventListener;
import vadintevem.events.EventNotifier;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;

@Singleton
public class EventNotifierStub implements EventNotifier {

    private final Collection<EventListener> listeners = new ArrayList<>();

    @Override
    public void notify(Event event) {
        listeners.forEach(listener -> listener.onEvent(event));
    }

    public void add(EventListener listener) {
        listeners.add(listener);
    }
}
