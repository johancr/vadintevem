package vadintevem.servlet;

import vadintevem.entities.User;
import vadintevem.reader.ReaderInteractor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Path("/history")
@Produces(MediaType.APPLICATION_JSON)
public class HistoryResource {

    private final ReaderInteractor readerInteractor;

    @Inject
    public HistoryResource(ReaderInteractor readerInteractor) {
        this.readerInteractor = readerInteractor;
    }

    @GET
    public Response history(@QueryParam("username") String username) {
        return Optional.ofNullable(username)
                .map(User::of)
                .map(readerInteractor::loadHistory)
                .map(history -> history.stream().map(MessageDto::from).collect(toList()))
                .map(Response::ok)
                .orElse(Response.status(BAD_REQUEST))
                .build();
    }
}
