package vadintevem.objectify;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import vadintevem.entities.Message;

@Entity
public class MessageEntity extends Message {

    @Id
    private Long id;

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

    @Override
    public String getContent() {
        return content;
    }
}
