package org.ldclrcq.agistrava.domain.users

interface StravaUserProvider {
    suspend fun currentUser(): StravaUser
}