package vadintevem.stories;

import cucumber.api.java8.En;
import vadintevem.entities.Message;
import vadintevem.messages.Messages;
import vadintevem.publisher.PublisherInteractor;
import vadintevem.ranking.Ranker;
import vadintevem.reader.impl.ReaderInteractor;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Steps implements En {

    private final Message message = Message.of("test message");
    private final Message reaction = Message.of("reaction message");

    private Message fetched;

    @Inject
    public Steps(PublisherInteractor publisherInteractor,
                 ReaderInteractor readerInteractor,
                 Messages messages,
                 Ranker ranker) {

        Given("^a message is published$", () -> {
            publisherInteractor.publish(message);
        });

        Given("^a message is fetched$", () -> {
            fetched = readerInteractor.findMessage().orElseThrow(IllegalStateException::new);
        });

        When("^reacting to that message$", () -> {
            publisherInteractor.publish(reaction);
        });

        When("^requesting next message$", () -> {
            readerInteractor.nextMessage(fetched);
        });

        Then("^the reaction is published$", () -> {
            assertThat(messages.findAll().stream()
                    .anyMatch(m -> m.getContent().equals(reaction.getContent())), is(true));
        });

        Then("^the rank of the message is increased$", () -> {
            long rank = ranker.findRank(fetched).orElseThrow(IllegalStateException::new);
            assertThat(rank, is(1L));
        });
    }
}
