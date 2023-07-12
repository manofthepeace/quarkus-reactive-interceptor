package org.acme;

import org.acme.interceptor.RestPrerequisite;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@RegisterRestClient(baseUri = "http://localhost:8080")
@RestPrerequisite
public interface RemoteApi {

    @GET
    @Path("/hello")
    Uni<String> hello();

    @GET
    @Path("/prereq")
    Uni<Boolean> prerequisite();

}
