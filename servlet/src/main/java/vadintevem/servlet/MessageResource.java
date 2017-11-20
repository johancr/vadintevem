package vadintevem.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    @GET
    public Response message() {
        return Response.ok(new MessageDto("message at time " + Instant.now())).build();
    }
}
