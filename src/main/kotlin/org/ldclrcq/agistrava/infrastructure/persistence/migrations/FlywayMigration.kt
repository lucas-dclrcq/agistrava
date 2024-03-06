package org.ldclrcq.agistrava.infrastructure.persistence.migrations

import io.quarkus.runtime.Startup
import io.quarkus.scheduler.Scheduler
import io.smallrye.config.ConfigMapping
import io.smallrye.config.WithDefault
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.flywaydb.core.Flyway
import org.hibernate.SessionFactory

@Startup
class FlywayMigration(
    scheduler: Scheduler,
    flywayConfig: FlywayConfig,
    sessionFactory: SessionFactory,
    @ConfigProperty(name = "quarkus.datasource.reactive.url") datasourceUrl: String,
    @ConfigProperty(name = "quarkus.datasource.username") datasourceUsername: String?,
    @ConfigProperty(name = "quarkus.datasource.password") datasourcePassword: String?
) {
    init {
        val flyway = Flyway
            .configure()
            .dataSource(datasourceUrl.replace("vertx-reactive:", "jdbc:"), datasourceUsername, datasourcePassword)
            .cleanDisabled(!flywayConfig.cleanAtStart())
            .load()

        if (flywayConfig.migrateAtStart()) {
            scheduler.pause()
            if (flywayConfig.cleanAtStart()) {
                flyway.clean()
            }
            flyway.migrate()
            sessionFactory.schemaManager.validateMappedObjects()
            scheduler.resume()
        }
    }


}