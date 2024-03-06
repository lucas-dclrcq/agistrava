package org.ldclrcq.agistrava.application.queries

import com.trendyol.kediatr.Query
import com.trendyol.kediatr.QueryHandler
import io.quarkus.runtime.Startup
import jakarta.enterprise.context.ApplicationScoped
import org.ldclrcq.agistrava.domain.users.StravaUser
import org.ldclrcq.agistrava.domain.users.StravaUserProvider

object GetCurrentUser : Query<StravaUser>

@ApplicationScoped
@Startup
class GetCurrentUserHandler(private val stravaUserProvider: StravaUserProvider) : QueryHandler<GetCurrentUser, StravaUser> {
    override suspend fun handle(query: GetCurrentUser): StravaUser {
        return this.stravaUserProvider.currentUser()
    }
}