package vadintevem.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PublishDto {

    private final MessageDto message;
    private final AuthorDto author;

    public PublishDto(@JsonProperty("message") MessageDto message,
                      @JsonProperty("author") AuthorDto author) {
        this.message = message;
        this.author = author;
    }

    public MessageDto getMessage() {
        return message;
    }

    public AuthorDto getAuthor() {
        return author;
    }
}
