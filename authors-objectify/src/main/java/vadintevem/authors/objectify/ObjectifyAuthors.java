package vadintevem.authors.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import vadintevem.authors.Authors;
import vadintevem.authors.admin.AuthorsAdmin;
import vadintevem.base.functional.Either;
import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.messages.objectify.MessageEntity;

import java.util.Collection;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static java.util.stream.Collectors.toList;

public class ObjectifyAuthors implements Authors, AuthorsAdmin {

    @Override
    public void append(Author author, long messageId) {
        AuthorEntity entity = getEntity(author);
        Key<MessageEntity> messageEntity = Key.create(MessageEntity.class, messageId);
        entity.add(Ref.create(messageEntity));
        ofy().save().entity(entity).now();
    }

    private AuthorEntity getEntity(Author author) {
        AuthorEntity entity = ofy().load().type(AuthorEntity.class).filter("username", author.getId()).first().now();
        return entity != null
                ? entity
                : AuthorEntity.from(author);
    }

    @Override
    public Either<String, Collection<Message>> findWrittenBy(Author author) {
        Either<String, AuthorEntity> entity = find(author);
        return entity.map(a -> a.getAuthored().stream()
                .map(Ref::get)
                .map(MessageEntity::toDomain)
                .collect(toList()));
    }

    private Either<String, AuthorEntity> find(Author author) {
        AuthorEntity entity = ofy().load().type(AuthorEntity.class).filter("username", author.getId()).first().now();
        return entity != null
                ? Either.right(entity)
                : Either.left("Author with id " + author.getId() + " not found");
    }

    @Override
    public void deleteAll() {
        ofy().delete().keys(ofy().load().type(AuthorEntity.class).keys());
    }
}
