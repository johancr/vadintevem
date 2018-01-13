package vadintevem.publisher;

import vadintevem.entities.User;
import vadintevem.entities.Message;

public abstract class PublishMessageRequest {

    public static PublishMessageRequest of(Message previous, Message message, User user) {
        return new PublishMessageRequest() {

            @Override
            public Message getPrevious() {
                return previous;
            }

            @Override
            public Message getMessage() {
                return message;
            }

            @Override
            public User getUser() {
                return user;
            }
        };
    }

    public abstract Message getPrevious();
    public abstract Message getMessage();
    public abstract User getUser();
}
