package org.ldclrcq.agistrava.infrastructure.auth

import io.quarkus.oidc.runtime.AbstractJsonObjectResponse
import io.quarkus.security.identity.SecurityIdentity
import jakarta.enterprise.context.RequestScoped

private const val AUTH_ID = "id"
private const val FIRSTNAME = "firstname"
private const val LASTNAME = "lastname"
private const val USERINFO = "userinfo"

@RequestScoped
class StravaOidcSecurity(private val securityIdentity: SecurityIdentity) {
    fun getOidcFirstName(): String = this.userInfo().getString(FIRSTNAME)

    fun getOidcLastName(): String = this.userInfo().getString(LASTNAME)

    fun getAuthId(): Long = this.userInfo().getLong(AUTH_ID)

    private fun userInfo() = securityIdentity.attributes[USERINFO] as AbstractJsonObjectResponse
}

// TODO: use extension methods for all attributes
fun SecurityIdentity.getLastName(): String =
    (this.attributes[USERINFO] as AbstractJsonObjectResponse).getString(LASTNAME)