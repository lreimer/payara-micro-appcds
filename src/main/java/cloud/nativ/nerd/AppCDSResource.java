package cloud.nativ.nerd;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Path("/appcds")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AppCDSResource {

    @Inject
    @ConfigProperty(name = "sleep.bound", defaultValue = "250")
    private int sleepBound;

    @Inject
    @ConfigProperty(name = "appcds.enabled", defaultValue = "false")
    private boolean appcdsEnabled;

    @GET
    @Timed(name = "getAppCDS")
    public Response getAppCDS() {
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(sleepBound));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Map<String, Object> payload = Collections.singletonMap("appcds.enabled", appcdsEnabled);
        return Response.ok(payload).build();
    }
}
