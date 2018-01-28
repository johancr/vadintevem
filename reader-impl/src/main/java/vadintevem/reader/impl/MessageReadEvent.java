package vadintevem.reader.impl;

import vadintevem.entities.Message;
import vadintevem.entities.User;
import vadintevem.events.Event;

import java.util.HashMap;
import java.util.Map;

public class MessageReadEvent extends Event {

    private final User author;
    private final Map<String, Object> data = new HashMap<>();

    public MessageReadEvent(User author, Message message) {
        this.author = author;
        this.data.put("messageId", message.getId());
    }

    @Override
    public String getUsername() {
        return author.getUsername();
    }

    @Override
    public Map<String, Object> getData() {
        return data;
    }
}
