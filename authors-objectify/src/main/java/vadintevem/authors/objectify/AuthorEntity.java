package vadintevem.authors.objectify;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import vadintevem.entities.Author;
import vadintevem.messages.objectify.MessageEntity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AuthorEntity {

    @Id
    private Long id;

    @Index
    private String username;

    private Set<Ref<MessageEntity>> authored;

    private AuthorEntity(String username) {
        this.username = username;
        this.authored = new HashSet<>();
    }

    private AuthorEntity() {
        // for objectify
    }

    public static AuthorEntity from(Author author) {
        return new AuthorEntity(author.getId());
    }

    public Collection<Ref<MessageEntity>> getAuthored() {
        return authored;
    }

    public void add(Ref<MessageEntity> message) {
        authored.add(message);
    }
}
