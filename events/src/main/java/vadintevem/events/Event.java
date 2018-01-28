package vadintevem.events;

import java.util.Map;

public abstract class Event {

    public abstract String getUsername();

    public abstract Map<String, Object> getData();
}
