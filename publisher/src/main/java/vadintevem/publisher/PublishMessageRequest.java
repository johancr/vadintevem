package vadintevem.publisher;

import vadintevem.entities.Author;
import vadintevem.entities.Message;

public abstract class PublishMessageRequest {

    public static PublishMessageRequest of(Message previous, Message message, Author author) {
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
            public Author getAuthor() {
                return author;
            }
        };
    }

    public abstract Message getPrevious();
    public abstract Message getMessage();
    public abstract Author getAuthor();
}
