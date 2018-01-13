package vadintevem.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;
import vadintevem.entities.User;

public class UserDto {

    private final String username;

    public UserDto(@JsonProperty("username") String username) {
        this.username = username;
    }

    public User toEntity() {
        return User.of(username);
    }
}
