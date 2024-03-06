package org.ldclrcq.agistrava.infrastructure.auth

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.BadRequestException
import org.ldclrcq.agistrava.domain.users.StravaUser
import org.ldclrcq.agistrava.domain.users.StravaUserId
import org.ldclrcq.agistrava.domain.users.StravaUserProvider
import org.ldclrcq.agistrava.domain.users.StravaUserRepository

@ApplicationScoped
class OidcUserProvider(private val stravaOidcSecurity: StravaOidcSecurity, private val stravaUserRepository: StravaUserRepository) : StravaUserProvider {
    override suspend fun currentUser(): StravaUser =
        stravaOidcSecurity.getAuthId().let { this.stravaUserRepository.get(StravaUserId(it)) ?: throw BadRequestException() }
}