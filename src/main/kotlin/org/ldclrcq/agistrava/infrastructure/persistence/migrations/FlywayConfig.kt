package org.ldclrcq.agistrava.infrastructure.persistence.migrations

import io.smallrye.config.ConfigMapping
import io.smallrye.config.WithDefault

@ConfigMapping(prefix = "application.database.flyway")
interface FlywayConfig {
    fun migrateAtStart(): Boolean

    @WithDefault("false")
    fun cleanAtStart(): Boolean
}