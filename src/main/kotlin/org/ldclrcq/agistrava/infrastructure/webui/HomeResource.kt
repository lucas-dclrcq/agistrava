package org.ldclrcq.agistrava.infrastructure.webui

import com.trendyol.kediatr.Mediator
import io.quarkus.security.Authenticated
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import org.ldclrcq.agistrava.application.queries.GetCurrentUser

@Authenticated
@Path("/")
class HomeResource(private val mediator: Mediator) {
    @GET
    @Produces("text/html")
    suspend fun test() : String {
        return mediator.send(GetCurrentUser).lastName.value
    }
}