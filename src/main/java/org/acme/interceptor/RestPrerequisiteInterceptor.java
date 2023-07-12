package org.acme.interceptor;

import org.acme.RemoteApi;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@RestPrerequisite
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class RestPrerequisiteInterceptor {

    @RestClient
    RemoteApi api;

    private static final Logger LOG = Logger.getLogger(RestPrerequisiteInterceptor.class);

    @AroundInvoke
    Object theInterceptor(InvocationContext context) throws Throwable {
        if (context.getMethod().getName().equals("prerequisite")) {
            return context.proceed();
        }

        Object ret = null;
        try {
            ret = context.proceed();
            if (ret instanceof Uni uni) {
                return api.prerequisite().chain(bool -> {
                    if (bool) {
                        return (Uni<?>) uni;
                    }
                    return Uni.createFrom().nullItem();
                });
            }
        } catch (Exception e) {
            LOG.error("Uni interceptor failed", e);
        }
        return ret;
    };
}
