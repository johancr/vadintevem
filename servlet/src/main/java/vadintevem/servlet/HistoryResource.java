package vadintevem.servlet;

import vadintevem.reader.impl.ReaderInteractor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.stream.Collectors.toList;

@Path("/history")
@Produces(MediaType.APPLICATION_JSON)
public class HistoryResource {

    private final ReaderInteractor readerInteractor;

    @Inject
    public HistoryResource(ReaderInteractor readerInteractor) {
        this.readerInteractor = readerInteractor;
    }

    @GET
    public Response history() {
        return Response.ok(readerInteractor.loadHistory().stream().
                map(MessageDto::from).collect(toList())).build();
    }

    @POST
    public Response save(MessageDto message) {
        if (message.getId() != null)
        {
            readerInteractor.saveHistory(message.toEntity());
            return Response.ok().build();
        }
        else
        {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
