package vadintevem.stories;

import cucumber.api.java8.En;
import vadintevem.entities.Message;
import vadintevem.messages.Messages;
import vadintevem.publisher.PublisherInteractor;
import vadintevem.reader.impl.ReaderInteractor;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Steps implements En {

    private final Message message = Message.of("test message");
    private final Message reaction = Message.of("reaction message");

    @Inject
    private PublisherInteractor publisherInteractor;

    @Inject
    private ReaderInteractor readerInteractor;

    @Inject
    private Messages messages;

    private Message fetched;

    public Steps() {

        Given("^a message is published$", () -> {
            publisherInteractor.publish(message);
        });

        Given("^a message is fetched$", () -> {
            fetched = readerInteractor.findMessage().orElseThrow(IllegalStateException::new);
        });

        When("^reacting to that message$", () -> {
            publisherInteractor.publish(reaction);
        });

        Then("^the reaction is published$", () -> {
            assertThat(messages.findAll().stream()
                    .anyMatch(m -> m.getContent().equals(reaction.getContent())), is(true));
        });
    }
}
