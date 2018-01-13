package vadintevem.stories;

import cucumber.api.java8.En;
import vadintevem.base.functional.Either;
import vadintevem.base.functional.List;
import vadintevem.entities.Message;
import vadintevem.messages.Messages;
import vadintevem.publisher.PublisherInteractor;
import vadintevem.ranking.Ranker;
import vadintevem.reader.FindMessageRequest;
import vadintevem.reader.ReaderInteractor;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static java.util.function.Function.identity;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static vadintevem.stories.Data.*;

public class AnonymousSteps implements En {

    @Inject
    public AnonymousSteps(PublisherInteractor publisherInteractor,
                          ReaderInteractor readerInteractor,
                          Messages messages,
                          Ranker ranker,
                          State state) {

        Given("^a message is published$", () -> {
            publisherInteractor.publish(MESSAGE, UNKNOWN_USER);
        });

        When("^a too long message is published$", () -> {
            int tooLong = 141;
            Either<List<String>, Void> result = publisherInteractor.publish(Message.of(generate(tooLong)), UNKNOWN_USER);
            state.setPublishResult(result);
        });

        When("^a random message is fetched$", () -> {
            Optional<Message> fetched = readerInteractor.findRandomMessage();
            state.setFetched(fetched);
        });

        When("^a message is fetched$", () -> {
            Optional<Message> fetched = readerInteractor.findMessage(FindMessageRequest.of(UNKNOWN_USER));
            state.setFetched(fetched);
        });

        When("^another message is fetched$", () -> {
            Optional<Message> fetched = readerInteractor.findMessage(FindMessageRequest.of(UNKNOWN_USER));
            state.setFetched(fetched);
        });

        When("^the history is fetched$", () -> {
            Collection<Message> history = readerInteractor.loadHistory(UNKNOWN_USER);
            state.setHistory(history);
        });

        Then("^the published message is fetched$", () -> {
            Either<String, Collection<Message>> published = state.getPublished();
            Collection<Message> authored = published.fold(error -> Collections.emptyList(), identity());
            assertThat(authored.stream().allMatch(m -> m.getContent().equals(MESSAGE.getContent())), is(true));
        });

        Then("^the reaction is published$", () -> {
            assertThat(messages.findAll().stream()
                    .anyMatch(m -> m.getContent().equals(REACTION.getContent())), is(true));
        });

        Then("^the message is fetched$", () -> {
            Optional<Message> fetched = state.getFetched();
            assertThat(fetched.isPresent(), is(true));
        });

        Then("^no message is fetched$", () -> {
            Optional<Message> fetched = state.getFetched();
            assertThat(fetched.isPresent(), is(false));
        });

        Then("^the rank of the message is increased$", () -> {
            Optional<Message> fetched = state.getFetched();
            long rank = fetched.flatMap(ranker::findRank).orElseThrow(IllegalStateException::new);
            assertThat(rank, is(1L));
        });

        Then("^the rank of the message is not increased$", () -> {
            Optional<Message> fetched = state.getFetched();
            Optional<Long> rank = fetched.flatMap(ranker::findRank);
            assertThat(rank.orElse(0L), is(1L));
        });

        Then("^an error message is returned saying that the message is too long$", () -> {
            Either<List<String>, Void> publishResult = state.getPublishResult();
            List<String> errors = publishResult.fold(e -> e, v -> List.list());
            assertThat(errors.head(), is("Message cannot be longer than 140 characters"));
        });

        Then("^the history is empty$", () -> {
            Collection<Message> history = state.getHistory();
            assertThat(history.isEmpty(), is(true));
        });

        Then("^the history contains the fetched message$", () -> {
            Collection<Message> history = state.getHistory();
            Optional<Message> fetched = state.getFetched();

            assertThat(fetched.map(history::contains).orElse(false), is(true));
        });

    }

    private static String generate(int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, 'x');
        return new String(chars);
    }
}

