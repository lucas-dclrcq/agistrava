package org.ldclrcq.agistrava.infrastructure.strava

import jakarta.json.JsonObject
import jakarta.ws.rs.*

@Path("/athlete/activities")
interface StravaActivitiesApi {
    @GET
    @Produces("application/json")
    fun getLoggedInAthleteActivities(
        @QueryParam("before") before: Int?,
        @QueryParam("after") after: Int?,
        @QueryParam("page") page: Int?,
        @QueryParam("per_page") perPage: Int = 30
    ): List<JsonObject?>?
}

