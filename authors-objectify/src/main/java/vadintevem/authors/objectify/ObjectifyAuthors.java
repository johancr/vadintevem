package vadintevem.authors.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import vadintevem.authors.Authors;
import vadintevem.authors.admin.AuthorsAdmin;
import vadintevem.base.functional.Either;
import vadintevem.entities.User;
import vadintevem.entities.Message;
import vadintevem.messages.objectify.MessageEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static java.util.stream.Collectors.toList;

public class ObjectifyAuthors implements Authors, AuthorsAdmin {

    @Override
    public void publish(User user, Message message) {
        AuthorEntity entity = getEntity(user);
        Key<MessageEntity> messageEntity = Key.create(MessageEntity.class, message.getId());
        entity.add(Ref.create(messageEntity));
        ofy().save().entity(entity).now();
    }

    private AuthorEntity getEntity(User user) {
        AuthorEntity entity = ofy().load().type(AuthorEntity.class).filter("username", user.getUsername()).first().now();
        return entity != null
                ? entity
                : AuthorEntity.from(user);
    }

    @Override
    public Either<String, Collection<Message>> findWrittenBy(User user) {
        Either<String, AuthorEntity> entity = find(user);
        return entity.map(a -> a.getAuthored().stream()
                .map(Ref::get)
                .map(MessageEntity::toDomain)
                .collect(toList()));
    }

    @Override
    public Optional<User> findAuthorOf(Message message) {
        List<AuthorEntity> authors = ofy().load().type(AuthorEntity.class).list();
        return authors.stream()
                .filter(a -> a.getAuthored().stream()
                        .map(Ref::get)
                        .map(MessageEntity::toDomain)
                        .anyMatch(authored -> authored.getId().equals(message.getId())))
                .findAny().map(AuthorEntity::toDomain);
    }

    private Either<String, AuthorEntity> find(User user) {
        AuthorEntity entity = ofy().load().type(AuthorEntity.class).filter("username", user.getUsername()).first().now();
        return entity != null
                ? Either.right(entity)
                : Either.left("User with id " + user.getUsername() + " not found");
    }

    @Override
    public void deleteAll() {
        ofy().delete().keys(ofy().load().type(AuthorEntity.class).keys());
    }
}
