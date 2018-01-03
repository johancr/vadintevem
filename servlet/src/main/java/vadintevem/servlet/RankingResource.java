package vadintevem.servlet;

import vadintevem.explorer.ExplorerInteractor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.stream.Collectors.toList;

@Path("/ranking")
@Produces(MediaType.APPLICATION_JSON)
public class RankingResource {

    private final ExplorerInteractor explorerInteractor;

    @Inject
    public RankingResource(ExplorerInteractor explorerInteractor) {
        this.explorerInteractor = explorerInteractor;
    }

    @GET
    public Response top(@QueryParam("limit") int limit) {
        return Response.ok(explorerInteractor.top(limit).stream()
                .map(MessageDto::from)
                .collect(toList()))
                .build();
    }
}
