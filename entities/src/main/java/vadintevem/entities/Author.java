package vadintevem.entities;

public abstract class Author {

    public static Author of(String id) {
        return new Author() {
            @Override
            public String getId() {
                return id;
            }
        };
    }

    public abstract String getId();
}
