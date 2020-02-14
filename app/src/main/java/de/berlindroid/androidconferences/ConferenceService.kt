package de.berlindroid.androidconferences

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConferenceService {
    suspend fun fetchConferences(): List<ConferenceApi> = api.listRepos(
        version = "0.0.3",
        key = "7UF1tvyFxs4TXQDYq4btuIIfIuHvbSdz"
    )

    private val api = Retrofit.Builder()
        .baseUrl("https://wrapapi.com/use/mariobodemann/")
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()
        .create(ConferencesApi::class.java)
}
