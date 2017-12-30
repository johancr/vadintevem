package vadintevem.servlet;

import com.fasterxml.jackson.annotation.JsonProperty;
import vadintevem.entities.Author;

public class AuthorDto {

    private final String id;

    public AuthorDto(@JsonProperty("id") String id) {
        this.id = id;
    }

    public Author toEntity() {
        return Author.of(id);
    }
}
