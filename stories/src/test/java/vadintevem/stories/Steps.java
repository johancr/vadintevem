package vadintevem.stories;

import cucumber.api.java8.En;
import vadintevem.base.functional.Either;
import vadintevem.base.functional.List;
import vadintevem.entities.Message;
import vadintevem.messages.Messages;
import vadintevem.messages.admin.MessagesAdmin;
import vadintevem.publisher.PublisherInteractor;
import vadintevem.ranking.Ranker;
import vadintevem.reader.impl.ReaderInteractor;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Steps implements En {

    private final Message message = Message.of("test message");
    private final Message reaction = Message.of("reaction message");

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<Message> fetched;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<Message> nextFetched;

    private Either<List<String>, Void> result;

    @Inject
    public Steps(PublisherInteractor publisherInteractor,
                 ReaderInteractor readerInteractor,
                 Messages messages,
                 Ranker ranker,
                 MessagesAdmin messagesAdmin) {

        Before(() -> {
            messagesAdmin.deleteAll();
        });

        Given("^a message is published$", () -> {
            publisherInteractor.publish(message);
        });

        Given("^a message is fetched$", () -> {
            fetched = readerInteractor.findMessage();
        });

        When("^reacting to that message$", () -> {
            publisherInteractor.publish(reaction);
        });

        When("^requesting next message$", () -> {
            nextFetched = fetched.flatMap(readerInteractor::nextMessage);
        });

        When("^a too long message is published$", () -> {
            int tooLong = 141;
            result = publisherInteractor.publish(Message.of(generate(tooLong)));
        });

        Then("^the reaction is published$", () -> {
            assertThat(messages.findAll().stream()
                    .anyMatch(m -> m.getContent().equals(reaction.getContent())), is(true));
        });

        Then("^the rank of the message is increased$", () -> {
            long rank = fetched.flatMap(ranker::findRank).orElseThrow(IllegalStateException::new);
            assertThat(rank, is(1L));
        });

        Then("^an error message is returned saying that the message is too long$", () -> {
            List<String> errors = result.fold(e -> e, v -> List.list());
            assertThat(errors.head(), is("Message cannot be longer than 140 characters"));
        });

        Then("^no next message was fetched$", () -> {
            assertThat(nextFetched.isPresent(), is(false));
        });
    }

    private static String generate(int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, 'x');
        return new String(chars);
    }
}
