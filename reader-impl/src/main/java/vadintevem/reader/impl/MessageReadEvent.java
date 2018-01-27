package vadintevem.reader.impl;

import vadintevem.entities.User;
import vadintevem.events.Event;

public class MessageReadEvent extends Event {

    private final User author;

    public MessageReadEvent(User author) {
        this.author = author;
    }

    @Override
    public String getUsername() {
        return author.getUsername();
    }
}
