package vadintevem.reader;

import vadintevem.entities.Author;
import vadintevem.entities.Message;

public abstract class NextMessageRequest {

    public static NextMessageRequest of(Message previous, String algorithm, Author author) {
        return new NextMessageRequest() {
            @Override
            public Message getPrevious() {
                return previous;
            }

            @Override
            public String getAlgorithm() {
                return algorithm;
            }

            @Override
            public Author getAuthor() {
                return author;
            }
        };
    }

    public static NextMessageRequest of(Message previous, Author author) {
        return of(previous, "UNKNOWN", author);
    }

    public abstract Message getPrevious();
    public abstract String getAlgorithm();
    public abstract Author getAuthor();
}
