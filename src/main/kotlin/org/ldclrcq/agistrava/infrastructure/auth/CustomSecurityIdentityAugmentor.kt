package org.ldclrcq.agistrava.infrastructure.auth

import io.quarkus.oidc.UserInfo
import io.quarkus.security.identity.AuthenticationRequestContext
import io.quarkus.security.identity.SecurityIdentity
import io.quarkus.security.identity.SecurityIdentityAugmentor
import io.quarkus.security.runtime.QuarkusSecurityIdentity
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CustomSecurityIdentityAugmentor : SecurityIdentityAugmentor {
    override fun augment(identity: SecurityIdentity, context: AuthenticationRequestContext): Uni<SecurityIdentity> {
        val builder = QuarkusSecurityIdentity.builder(identity)
        val userInfo = identity.getAttribute<UserInfo>("userinfo")
        builder.setPrincipal { userInfo.getLong("id").toString() }

        return Uni.createFrom().item(builder.build())
    }
}