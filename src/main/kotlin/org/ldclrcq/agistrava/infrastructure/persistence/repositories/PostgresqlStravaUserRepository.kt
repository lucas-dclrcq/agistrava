package org.ldclrcq.agistrava.infrastructure.persistence.repositories

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase
import io.smallrye.mutiny.coroutines.awaitSuspending
import jakarta.enterprise.context.ApplicationScoped
import org.ldclrcq.agistrava.domain.users.*
import org.ldclrcq.agistrava.domain.users.StravaUserRepository
import org.ldclrcq.agistrava.infrastructure.persistence.entities.StravaUserEntity

@ApplicationScoped
class PostgresqlStravaUserRepository : PanacheRepositoryBase<StravaUserEntity, Long>, StravaUserRepository {
    override suspend fun get(id: StravaUserId): StravaUser? {
        return findById(id.value).awaitSuspending()
            ?.let { StravaUser(StravaUserId(it.id), StravaFirstName(it.firstname), StravaLastName(it.lastname), StravaCredentials(it.accessToken, it.refreshToken))}
    }

    override suspend fun save(stravaUser: StravaUser) {
        stravaUser
            .let { StravaUserEntity(it.id.value, it.firstName.value, it.lastName.value) }
            .also { persist(it).awaitSuspending() }
    }

}