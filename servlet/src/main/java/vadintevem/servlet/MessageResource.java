package vadintevem.servlet;

import vadintevem.publisher.PublisherInteractor;
import vadintevem.reader.impl.ReaderInteractor;

import javax.inject.Inject;
import javax.ws.rs.*;
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
    public Response message(@QueryParam("algorithm") String algorithm) {
        return algorithm != null
                ? preferredMessage(algorithm)
                : defaultMessage();
    }

    private Response preferredMessage(String algorithm) {
        return readerInteractor.findMessage(algorithm)
                .map(MessageDto::from)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    private Response defaultMessage() {
        return readerInteractor.findMessage()
                .map(MessageDto::from)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response publish(PublishDto data) {
        return publisherInteractor.publish(data.getMessage().toEntity(), data.getAuthor().toEntity())
                .fold(errors -> Response.status(400).entity(ErrorDto.from(errors.toArrayList())),
                        Response::ok)
                .build();
    }
}
