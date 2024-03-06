package org.ldclrcq.agistrava.domain.users

interface StravaUserRepository {
    suspend fun get(id: StravaUserId): StravaUser?
    suspend fun save(stravaUser: StravaUser)
}