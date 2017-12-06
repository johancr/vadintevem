package vadintevem.servlet;

import vadintevem.publisher.PublisherInteractor;
import vadintevem.reader.impl.ReaderInteractor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private final ReaderInteractor readerInteractor;
    private final PublisherInteractor publisherInteractor;

    @Inject
    public MessageResource(ReaderInteractor readerInteractor,
                           PublisherInteractor publisherInteractor) {
        this.readerInteractor = readerInteractor;
        this.publisherInteractor = publisherInteractor;
    }

    @GET
    public Response message() {
        return readerInteractor.findMessage()
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response publish(MessageDto message) {
        publisherInteractor.publish(message.toEntity());
        return Response.ok().build();
    }
}
