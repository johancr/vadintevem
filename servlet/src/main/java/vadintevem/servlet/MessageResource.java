package vadintevem.servlet;

import vadintevem.entities.User;
import vadintevem.entities.Message;
import vadintevem.publisher.PublisherInteractor;
import vadintevem.reader.FindMessageRequest;
import vadintevem.reader.NextMessageRequest;
import vadintevem.reader.ReaderInteractor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static java.util.Optional.empty;

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
    @Path("/find")
    public Response find(@QueryParam("algorithm") String algorithm,
                         @QueryParam("username") String username) {

        return findMessage(algorithm, username)
                .map(MessageDto::from)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    private Optional<Message> findMessage(String algorithm, String username) {
        return algorithm != null && username != null
                ? findMessage(algorithm, User.of(username))
                : defaultMessage();
    }

    private Optional<Message> findMessage(String algorithm, User user) {
        return readerInteractor.findMessage(FindMessageRequest.of(algorithm, user));
    }

    @POST
    @Path("/next")
    public Response next(@QueryParam("algorithm") String algorithm,
                            @QueryParam("username") String username,
                            MessageDto previous) {

        return nextMessage(algorithm, username, previous)
                .map(MessageDto::from)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    private Optional<Message> nextMessage(String algorithm, String username, MessageDto previous) {
        return algorithm != null && username != null
                ? nextMessage(algorithm, User.of(username), previous.toEntity())
                : empty();
    }

    private Optional<Message> nextMessage(String algorithm, User user, Message previous) {
        return readerInteractor.nextMessage(NextMessageRequest.of(previous, algorithm, user));
    }

    private Optional<Message> defaultMessage() {
        return readerInteractor.findRandomMessage();
    }

    @POST
    public Response publish(PublishDto data) {
        if (data.getMessage() != null && data.getUser() != null)
        {
            return publisherInteractor.publish(data.getMessage().toEntity(), data.getUser().toEntity())
                    .fold(errors -> Response.status(400).entity(ErrorDto.from(errors.toArrayList())),
                            Response::ok)
                    .build();
        }
        else
        {
            return Response.status(400).build();
        }
    }
}
