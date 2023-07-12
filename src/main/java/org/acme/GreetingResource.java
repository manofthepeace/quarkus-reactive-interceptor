package org.acme;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class GreetingResource {

    AtomicInteger cnt = new AtomicInteger();

    private static final Logger LOG = Logger.getLogger(GreetingResource.class);

    @RestClient
    RemoteApi api;

    @GET
    @Path("/go")
    public Uni<String> testApi() {
        return api.hello();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @NonBlocking
    @Path("/hello")
    public String hello() {
        LOG.info("Calling hello");
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @NonBlocking
    @Path("/prereq")
    public Boolean preRequisite() {
        if (cnt.getAndIncrement() % 3 == 0) {
            LOG.infof("Calling prereq, %b", true);
            return true;
        }
        LOG.infof("Calling prereq, %b", false);
        return false;
    }
}
