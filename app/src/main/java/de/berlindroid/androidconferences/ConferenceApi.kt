package de.berlindroid.androidconferences

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ConferencesApi {
    @GET("conferences/android/{version}")
    fun listRepos(
        @Path("version") version: String,
        @Query("wrapAPIKey") key: String
    ): Call<List<ConferenceApi>>
}

data class ConferenceApi(
    var name: String,
    var place: String
)

