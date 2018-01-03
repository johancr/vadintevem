package vadintevem.messages.objectify;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import vadintevem.entities.Message;

@Entity
public class MessageEntity {

    @Id
    private Long id;

    @Index
    private String content;

    public static MessageEntity from(Message message) {
        return new MessageEntity(message);
    }

    private MessageEntity(Message message) {
        this.content = message.getContent();
    }

    private MessageEntity() {
        // for objectify
    }

    public Message toDomain() {
        return Message.of(content).setId(id);
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }
}
