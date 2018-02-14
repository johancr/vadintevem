package vadintevem.events.remote;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import vadintevem.base.functional.Attempt;
import vadintevem.events.Event;
import vadintevem.events.EventNotifier;

import javax.inject.Inject;

import static vadintevem.base.functional.Attempt.checkedAttempt;


public class RemoteEventNotifier implements EventNotifier {

    private final HttpClient client = HttpClients.createDefault();
    private final ObjectWriter objectWriter = new ObjectMapper().writerFor(EventDto.class);
    private final RemoteEventConfiguration remoteEventConfiguration;

    @Inject
    public RemoteEventNotifier(RemoteEventConfiguration remoteEventConfiguration) {
        this.remoteEventConfiguration = remoteEventConfiguration;
    }

    @Override
    public void notify(Event event) {
        serialize(event)
                .map(this::createRequest)
                .flatMap(this::send)
                .forEachOrThrow(ignore -> {});
    }

    private Attempt<String> serialize(Event event) {
        return checkedAttempt(() -> objectWriter.writeValueAsString(EventDto.from(event)));
    }

    private HttpPost createRequest(String json) {
        HttpPost request = new HttpPost(remoteEventConfiguration.getHost() + "/events/notify");
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        request.setEntity(entity);
        return request;
    }

    private Attempt<?> send(HttpPost notify) {
        return checkedAttempt(() -> client.execute(notify));
    }
}
