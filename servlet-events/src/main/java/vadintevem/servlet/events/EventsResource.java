package vadintevem.servlet.events;

import vadintevem.events.EventNotifier;
import vadintevem.events.websocket.EventDto;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/notify")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventsResource {

    private final EventNotifier eventNotifier;

    @Inject
    public EventsResource(EventNotifier eventNotifier) {
        this.eventNotifier = eventNotifier;
    }

    @POST
    public Response onNotification(EventDto event) {
        eventNotifier.notify(event.toDomain());
        return Response.ok().build();
    }
}
