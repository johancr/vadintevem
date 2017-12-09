package vadintevem.servlet;

import vadintevem.publisher.PublisherInteractor;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ranking")
@Produces(MediaType.APPLICATION_JSON)
public class RankingResource {

    private final PublisherInteractor publisherInteractor;

    @Inject
    public RankingResource(PublisherInteractor publisherInteractor) {
        this.publisherInteractor = publisherInteractor;
    }

    @PUT
    @Path("/increase")
    public Response increaseRanking(MessageDto message) {
        publisherInteractor.increaseRanking(message.toEntity());
        return Response.ok().build();
    }
}
