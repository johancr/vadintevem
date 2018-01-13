package vadintevem.servlet;

import vadintevem.entities.User;
import vadintevem.publisher.PublisherInteractor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.stream.Collectors.toList;

@Path("/author")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private final PublisherInteractor publisherInteractor;

    @Inject
    public AuthorResource(PublisherInteractor publisherInteractor) {
        this.publisherInteractor = publisherInteractor;
    }

    @Path("{username}/message")
    @GET
    public Response message(@PathParam("username") String username) {
        return publisherInteractor.findWrittenBy(User.of(username))
                .fold(error -> Response.status(400).entity(ErrorDto.from(error)),
                        messages -> Response.ok(messages.stream().map(MessageDto::from).collect(toList())))
                .build();
    }
}
