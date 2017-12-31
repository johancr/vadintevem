package vadintevem.stories;

import cucumber.api.java8.En;
import vadintevem.base.functional.Either;
import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.publisher.PublisherInteractor;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

import static java.util.function.Function.identity;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AuthorSteps implements En {

    private final Message message = Message.of("test message");

    private Either<String, Collection<Message>> result;

    @Inject
    public AuthorSteps(PublisherInteractor publisherInteractor) {

        Given("^a message is published by user (\\w+)$", (String user) -> {
            publisherInteractor.publish(message, Author.of(user));
        });

        When("^user (\\w+) fetches his published messages$", (String user) -> {
            result = publisherInteractor.findWrittenBy(Author.of(user));
        });

        Then("^the published message is fetched$", () -> {
            Collection<Message> authored = result.fold(error -> Collections.emptyList(), identity());
            assertThat(authored.stream().allMatch(m -> m.getContent().equals(message.getContent())), is(true));
        });
    }
}
