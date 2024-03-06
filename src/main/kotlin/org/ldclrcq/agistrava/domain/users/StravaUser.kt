package org.ldclrcq.agistrava.domain.users

data class StravaUserId(val value: Long)

data class StravaFirstName(val value: String)

data class StravaLastName(val value: String)

data class StravaCredentials(val accessToken: String, val refreshToken: String)

data class StravaUser(val id: StravaUserId, val firstName: StravaFirstName, val lastName: StravaLastName, val stravaCredentials: StravaCredentials)
