package vadintevem.servlet;

import vadintevem.reader.ReaderInteractor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private final ReaderInteractor readerInteractor;

    @Inject
    public MessageResource(ReaderInteractor readerInteractor) {
        this.readerInteractor = readerInteractor;
    }

    @GET
    public Response message() {
        return readerInteractor.findMessage()
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }
}
