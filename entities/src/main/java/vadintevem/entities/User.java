package vadintevem.entities;

public abstract class User {

    public static User of(String username) {
        return new User() {
            @Override
            public String getUsername() {
                return username;
            }
        };
    }

    public abstract String getUsername();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getUsername() != null ? getUsername().equals(user.getUsername()) : user.getUsername() == null;
    }

    @Override
    public int hashCode() {
        return getUsername() != null ? getUsername().hashCode() : 0;
    }
}
