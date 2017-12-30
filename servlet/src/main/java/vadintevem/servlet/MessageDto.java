package vadintevem.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;
import vadintevem.entities.Message;

public class MessageDto {

    private final String content;
    private final Long id;

    public MessageDto(@JsonProperty("content") String content,
                      @JsonProperty("id") Long id) {
        this.content = content;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public static MessageDto from(Message message) {
        return new MessageDto(message.getContent(), message.getId());
    }

    public Message toEntity() {
        return Message.of(content).setId(id);
    }
}
