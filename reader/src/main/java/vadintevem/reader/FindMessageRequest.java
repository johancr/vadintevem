package vadintevem.reader;

import vadintevem.entities.Author;

public abstract class FindMessageRequest {

    public static FindMessageRequest of(String algorithm, Author author) {
        return new FindMessageRequest() {

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

    public static FindMessageRequest of(Author author) {
        return of("UNKNOWN", author);
    }

    public abstract String getAlgorithm();
    public abstract Author getAuthor();
}
