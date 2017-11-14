package vadintevem.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    @GET
    public MessageDto messages() {
        return new MessageDto("message");
    }
}
