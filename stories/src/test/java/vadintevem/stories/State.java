package vadintevem.stories;

import com.google.inject.Singleton;
import vadintevem.base.functional.Either;
import vadintevem.base.functional.List;
import vadintevem.entities.Message;
import vadintevem.events.Event;

import java.util.Collection;
import java.util.Optional;

@Singleton
public class State {

    private Optional<Message> fetched;
    private Collection<Message> history;
    private Either<String, Collection<Message>> published;
    private Either<List<String>, Void> publishResult;
    private Event event;

    public Optional<Message> getFetched() {
        return fetched;
    }

    public void setFetched(Optional<Message> fetched) {
        this.fetched = fetched;
    }

    public Collection<Message> getHistory() {
        return history;
    }

    public void setHistory(Collection<Message> history) {
        this.history = history;
    }

    public Either<String, Collection<Message>> getPublished() {
        return published;
    }

    public void setPublished(Either<String, Collection<Message>> published) {
        this.published = published;
    }

    public Either<List<String>, Void> getPublishResult() {
        return publishResult;
    }

    public void setPublishResult(Either<List<String>,Void> publishResult) {
        this.publishResult = publishResult;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
