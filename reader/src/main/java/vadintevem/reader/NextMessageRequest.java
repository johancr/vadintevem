package vadintevem.reader;

import vadintevem.entities.User;
import vadintevem.entities.Message;

public abstract class NextMessageRequest {

    public static NextMessageRequest of(Message previous, String algorithm, User user) {
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
            public User getUser() {
                return user;
            }
        };
    }

    public static NextMessageRequest of(Message previous, User user) {
        return of(previous, "UNKNOWN", user);
    }

    public abstract Message getPrevious();
    public abstract String getAlgorithm();
    public abstract User getUser();
}
