package org.ldclrcq.agistrava

import io.quarkiverse.renarde.Controller
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import jakarta.ws.rs.Path

class Application : Controller() {
    @CheckedTemplate
    internal object Templates {
        @JvmStatic
        external fun index(): TemplateInstance
    }

    @Path("/")
    fun index(): TemplateInstance {
        return Templates.index()
    }
}