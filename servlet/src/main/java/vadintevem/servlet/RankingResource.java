package vadintevem.servlet;

import vadintevem.entities.Message;
import vadintevem.explorer.ExplorerInteractor;
import vadintevem.publisher.PublisherInteractor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.function.Consumer;

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
        validate(message).accept(publisherInteractor::increaseRanking);
        return Response.noContent().build();
    }

    @PUT
    @Path("/decrease")
    public Response decreaseRanking(MessageDto message) {
        validate(message).accept(publisherInteractor::decreaseRanking);
        return Response.noContent().build();
    }

    private static Consumer<Consumer<Message>> validate(MessageDto dto) {
        return consumer -> {
            Message message = dto.toEntity();
            if (message.getId() != null)
            {
                consumer.accept(message);
            }
        };
    }

    @GET
    public Response top(@QueryParam("limit") int limit) {
        return Response.ok(explorerInteractor.top(limit).stream()
                .map(MessageDto::from)
                .collect(toList()))
                .build();
    }
}
