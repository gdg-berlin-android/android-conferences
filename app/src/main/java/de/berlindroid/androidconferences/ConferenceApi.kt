package de.berlindroid.androidconferences

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ConferencesApi {
    @GET("conferences/android/{version}")
    suspend fun listRepos(
        @Path("version") version: String,
        @Query("wrapAPIKey") key: String
    ): List<ConferenceApi>
}

data class ConferenceApi(
    val name: String,
    val place: String,
    val date: String,
    val link: String,
    val cfp: CallForParticipation?
) {
    data class CallForParticipation(
        val from: String,
        val to: String,
        val url: String
    )
}

