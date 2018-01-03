package vadintevem.servlet;

import vadintevem.entities.Author;
import vadintevem.entities.Message;
import vadintevem.publisher.PublisherInteractor;
import vadintevem.reader.FindMessageRequest;
import vadintevem.reader.NextMessageRequest;
import vadintevem.reader.ReaderInteractor;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static vadintevem.reader.NextMessageRequest.of;

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

    @POST
    @Path("/find")
    public Response find(@QueryParam("algorithm") String algorithm,
                            @QueryParam("author") String author,
                            MessageDto previous) {

        return findMessage(algorithm, author, previous)
                .map(MessageDto::from)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    private Optional<Message> findMessage(String algorithm, String author, MessageDto previous) {
        return algorithm != null && author != null
                ? findMessage(algorithm, Author.of(author), previous.toEntity())
                : defaultMessage();
    }

    private Optional<Message> findMessage(String algorithm, Author author, Message previous) {
        return readerInteractor.findMessage(FindMessageRequest.of(previous, algorithm, author));
    }

    @POST
    @Path("/next")
    public Response next(@QueryParam("algorithm") String algorithm,
                            @QueryParam("author") String author,
                            MessageDto previous) {

        return nextMessage(algorithm, author, previous)
                .map(MessageDto::from)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    private Optional<Message> nextMessage(String algorithm, String author, MessageDto previous) {
        return algorithm != null && author != null
                ? nextMessage(algorithm, Author.of(author), previous.toEntity())
                : defaultMessage();
    }

    private Optional<Message> nextMessage(String algorithm, Author author, Message previous) {
        return readerInteractor.nextMessage(NextMessageRequest.of(previous, algorithm, author));
    }

    private Optional<Message> defaultMessage() {
        return readerInteractor.findMessage();
    }

    @POST
    public Response publish(PublishDto data) {
        if (data.getMessage() != null && data.getAuthor() != null)
        {
            return publisherInteractor.publish(data.getMessage().toEntity(), data.getAuthor().toEntity())
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
