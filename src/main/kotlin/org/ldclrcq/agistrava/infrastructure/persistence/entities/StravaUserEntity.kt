package org.ldclrcq.agistrava.infrastructure.persistence.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant
import java.time.Instant.now

@Entity(name = "strava_users")
class StravaUserEntity(
    @Id
    open var id: Long = 0,
    open var firstname: String = "",
    open var lastname: String = "",
    @Column(name = "refresh_token")
    open var refreshToken: String = "",
    @Column(name = "access_token")
    open var accessToken: String = "",
    open var createdAt: Instant = now(),
) {
    final override fun hashCode(): Int = id.hashCode()
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StravaUserEntity) return false
        if (id != other.id) return false
        return true
    }
}
