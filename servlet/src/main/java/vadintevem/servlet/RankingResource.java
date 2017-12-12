package vadintevem.servlet;

import vadintevem.explorer.ExplorerInteractor;
import vadintevem.publisher.PublisherInteractor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.stream.Collectors.toList;

@Path("/ranking")
@Produces(MediaType.APPLICATION_JSON)
public class RankingResource {

    private final PublisherInteractor publisherInteractor;
    private final ExplorerInteractor explorerInteractor;

    @Inject
    public RankingResource(PublisherInteractor publisherInteractor,
                           ExplorerInteractor explorerInteractor) {
        this.publisherInteractor = publisherInteractor;
        this.explorerInteractor = explorerInteractor;
    }

    @PUT
    @Path("/increase")
    public Response increaseRanking(MessageDto message) {
        publisherInteractor.increaseRanking(message.toEntity());
        return Response.noContent().build();
    }

    @PUT
    @Path("/decrease")
    public Response decreaseRanking(MessageDto message) {
        publisherInteractor.decreaseRanking(message.toEntity());
        return Response.noContent().build();
    }

    @GET
    public Response top(@QueryParam("limit") int limit) {
        return Response.ok(explorerInteractor.top(limit).stream()
                .map(MessageDto::from)
                .collect(toList()))
                .build();
    }
}
