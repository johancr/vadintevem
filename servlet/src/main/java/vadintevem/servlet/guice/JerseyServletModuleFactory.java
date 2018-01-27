package vadintevem.servlet.guice;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Module;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import vadintevem.servlet.*;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

public class JerseyServletModuleFactory {

    public static Module create() {
        return new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                bind(GuiceContainer.class);
                bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
                bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);
                bind(MessageResource.class);
                bind(HistoryResource.class);
                bind(RankingResource.class);
                bind(AuthorResource.class);

                serve("/service/*").with(GuiceContainer.class);
            }
        };
    }
}
