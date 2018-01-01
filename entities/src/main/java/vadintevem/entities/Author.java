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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return getId() != null ? getId().equals(author.getId()) : author.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
