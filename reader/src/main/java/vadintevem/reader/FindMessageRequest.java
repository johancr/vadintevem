package vadintevem.reader;

import vadintevem.entities.User;

public abstract class FindMessageRequest {

    public static FindMessageRequest of(String algorithm, User user) {
        return new FindMessageRequest() {

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

    public static FindMessageRequest of(User user) {
        return of("UNKNOWN", user);
    }

    public abstract String getAlgorithm();
    public abstract User getUser();
}
