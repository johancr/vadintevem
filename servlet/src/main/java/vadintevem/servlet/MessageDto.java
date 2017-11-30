package vadintevem.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;
import vadintevem.entities.Message;

public class MessageDto extends Message {

    private final String content;

    public MessageDto(@JsonProperty("content") String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    public static MessageDto from(Message message) {
        return new MessageDto(message.getContent());
    }

    public Message toEntity() {
        return Message.of(content);
    }
}
