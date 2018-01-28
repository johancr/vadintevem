package vadintevem.events.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import vadintevem.events.Event;

import java.util.Map;

public class EventDto {

    private String username;
    private Map<String, Object> data;

    public EventDto(@JsonProperty("username") String username,
                    @JsonProperty("data") Map<String, Object> data) {
        this.username = username;
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public static EventDto from(Event event) {
        return new EventDto(event.getUsername(), event.getData());
    }
}
