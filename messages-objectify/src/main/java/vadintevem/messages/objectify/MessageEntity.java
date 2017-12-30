package vadintevem.messages.objectify;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import vadintevem.entities.Message;

@Entity
public class MessageEntity extends Message {

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

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public Long getId() {
        return id;
    }
}
