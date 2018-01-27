package vadintevem.events.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import vadintevem.events.Event;

public class EventDto {

    private String username;

    public EventDto(@JsonProperty("username") String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public static EventDto from(Event event) {
        return new EventDto(event.getUsername());
    }
}
