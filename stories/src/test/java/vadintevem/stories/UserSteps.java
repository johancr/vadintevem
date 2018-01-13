package vadintevem.stories;

import cucumber.api.java8.En;
import vadintevem.base.functional.Either;
import vadintevem.entities.User;
import vadintevem.entities.Message;
import vadintevem.publisher.PublishMessageRequest;
import vadintevem.publisher.PublisherInteractor;
import vadintevem.reader.FindMessageRequest;
import vadintevem.reader.NextMessageRequest;
import vadintevem.reader.ReaderInteractor;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

import static vadintevem.stories.Data.MESSAGE;
import static vadintevem.stories.Data.REACTION;

public class UserSteps implements En {

    @Inject
    public UserSteps(ReaderInteractor readerInteractor,
                     PublisherInteractor publisherInteractor,
                     State state) {

        Given("^a message is published by user (\\w+)$", (String username) -> {
            publisherInteractor.publish(MESSAGE, User.of(username));
        });

        When("^a message is fetched by user (\\w+)$", (String username) -> {
            Optional<Message> fetched = readerInteractor.findMessage(FindMessageRequest.of("UNREAD", User.of(username)));
            state.setFetched(fetched);
        });

        When("^a random message is fetched by user (\\w+)$", (String username) -> {
            Optional<Message> fetched = readerInteractor.findMessage(FindMessageRequest.of("RANDOM", User.of(username)));
            state.setFetched(fetched);
        });

        When("^user (\\w+) reacts to that message$", (String username) -> {
            state.getFetched().ifPresent(previous ->
                    publisherInteractor.publish(PublishMessageRequest.of(previous, REACTION, User.of(username)))
            );
        });

        When("^a message is read by user (\\w+)$", (String username) -> {
            User user = User.of(username);
            Optional<Message> fetched = readerInteractor.findMessage(FindMessageRequest.of(user));
            fetched = fetched.flatMap(previous -> readerInteractor.nextMessage(NextMessageRequest.of(previous, user)));
            state.setFetched(fetched);
        });

        When("^user (\\w+) looks at his history$", (String username) -> {
            Collection<Message> history = readerInteractor.loadHistory(User.of(username));
            state.setHistory(history);
        });

        When("^user (\\w+) fetches his published messages$", (String username) -> {
            Either<String, Collection<Message>> published = publisherInteractor.findWrittenBy(User.of(username));
            state.setPublished(published);
        });
    }
}
