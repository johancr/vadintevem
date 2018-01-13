package vadintevem.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PublishDto {

    private final MessageDto message;
    private final UserDto user;

    public PublishDto(@JsonProperty("message") MessageDto message,
                      @JsonProperty("user") UserDto user) {
        this.message = message;
        this.user = user;
    }

    public MessageDto getMessage() {
        return message;
    }

    public UserDto getUser() {
        return user;
    }
}
