package org.ldclrcq.agistrava.application.middlewares

import com.trendyol.kediatr.PipelineBehavior
import io.quarkus.hibernate.reactive.panache.Panache
import io.quarkus.runtime.Startup
import io.smallrye.mutiny.coroutines.asUni
import io.smallrye.mutiny.coroutines.awaitSuspending
import jakarta.enterprise.context.ApplicationScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

@Startup
@ApplicationScoped
class TransactionalMiddleware : PipelineBehavior {
    override suspend fun <TRequest, TResponse> handle(
        request: TRequest,
        next: suspend (TRequest) -> TResponse
    ): TResponse {
        return transaction {
            next(request)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun <T> transaction(block: suspend () -> T): T = coroutineScope {
        Panache.withTransaction {
            this.async { block() }.asUni()
        }.awaitSuspending()
    }
}