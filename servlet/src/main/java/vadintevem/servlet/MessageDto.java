package vadintevem.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;
import vadintevem.entities.Message;

public class MessageDto extends Message {

    private final String content;

    public MessageDto(@JsonProperty String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}
