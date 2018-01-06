package vadintevem.stories;

import cucumber.api.java8.En;
import vadintevem.base.functional.Either;
import vadintevem.entities.Author;
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

        Given("^a message is published by user (\\w+)$", (String user) -> {
            publisherInteractor.publish(MESSAGE, Author.of(user));
        });

        When("^a message is fetched by user (\\w+)$", (String user) -> {
            Optional<Message> fetched = readerInteractor.findMessage(FindMessageRequest.of("UNREAD", Author.of(user)));
            state.setFetched(fetched);
        });

        When("^a random message is fetched by user (\\w+)$", (String user) -> {
            Optional<Message> fetched = readerInteractor.findMessage(FindMessageRequest.of("RANDOM", Author.of(user)));
            state.setFetched(fetched);
        });

        When("^user (\\w+) reacts to that message$", (String user) -> {
            state.getFetched().ifPresent(previous ->
                    publisherInteractor.publish(PublishMessageRequest.of(previous, REACTION, Author.of(user)))
            );
        });

        When("^a message is read by user (\\w+)$", (String user) -> {
            Author author = Author.of(user);
            Optional<Message> fetched = readerInteractor.findMessage(FindMessageRequest.of(author));
            fetched = fetched.flatMap(previous -> readerInteractor.nextMessage(NextMessageRequest.of(previous, author)));
            state.setFetched(fetched);
        });

        When("^user (\\w+) looks at his history$", (String user) -> {
            Collection<Message> history = readerInteractor.loadHistory(Author.of(user));
            state.setHistory(history);
        });

        When("^user (\\w+) fetches his published messages$", (String user) -> {
            Either<String, Collection<Message>> published = publisherInteractor.findWrittenBy(Author.of(user));
            state.setPublished(published);
        });
    }
}
