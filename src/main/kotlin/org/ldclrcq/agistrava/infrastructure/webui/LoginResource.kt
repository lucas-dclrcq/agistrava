package org.ldclrcq.agistrava.infrastructure.webui

import com.trendyol.kediatr.Mediator
import io.quarkus.oidc.AccessTokenCredential
import io.quarkus.security.Authenticated
import io.quarkus.security.identity.SecurityIdentity
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.UriBuilder
import org.ldclrcq.agistrava.application.commands.CreateUserIfNotExist
import org.ldclrcq.agistrava.infrastructure.auth.StravaOidcSecurity

@Path("/login-success")
@Authenticated
class LoginResource(private val mediator: Mediator, private val stravaOidcSecurity: StravaOidcSecurity, private val credentials: AccessTokenCredential) {

    @Produces("text/html")
    @GET
    suspend fun onLoginSuccess(): Response {
        this.mediator.send(CreateUserIfNotExist(stravaOidcSecurity.getAuthId(), stravaOidcSecurity.getOidcFirstName(), stravaOidcSecurity.getOidcLastName(), credentials.token, credentials.refreshToken.token))
        return Response.seeOther(UriBuilder.fromUri("/").build()).build()
    }
}