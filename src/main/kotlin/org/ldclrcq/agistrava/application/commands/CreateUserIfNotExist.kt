package org.ldclrcq.agistrava.application.commands

import com.trendyol.kediatr.Command
import com.trendyol.kediatr.CommandHandler
import io.quarkus.runtime.Startup
import jakarta.enterprise.context.ApplicationScoped
import org.jboss.logging.Logger
import org.ldclrcq.agistrava.domain.users.*

private val logger: Logger = Logger.getLogger(CreateUserIfNotExistHandler::class.java)
data class CreateUserIfNotExist(val authId: Long, val firstName: String, val lastName: String, val accessToken: String, val refreshToken: String): Command

@ApplicationScoped
@Startup
class CreateUserIfNotExistHandler(private val stravaUserRepository: StravaUserRepository) : CommandHandler<CreateUserIfNotExist> {
    override suspend fun handle(command: CreateUserIfNotExist) {
        if (this.stravaUserRepository.get(StravaUserId(command.authId)) != null) {
            return
        }

        val stravaUserId = StravaUserId(command.authId)
        val stravaFirstname = StravaFirstName(command.firstName)
        val stravaLastname = StravaLastName(command.lastName)
        val credentials = StravaCredentials(command.accessToken, command.refreshToken)

        val stravaUser = StravaUser(stravaUserId, stravaFirstname, stravaLastname, credentials)

        stravaUserRepository.save(stravaUser)

        logger.info("Created new strava user $stravaUser")
    }

}
