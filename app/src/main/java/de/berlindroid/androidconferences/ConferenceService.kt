package de.berlindroid.androidconferences

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConferenceService {
    fun fetchConferences(
        onError: (Throwable) -> Unit,
        onSuccess: (List<ConferenceApi>) -> Unit
    ) {
        api.listRepos(
            version = "0.0.3",
            key = "7UF1tvyFxs4TXQDYq4btuIIfIuHvbSdz"
        ).enqueue(object : Callback<List<ConferenceApi>> {
            override fun onFailure(call: Call<List<ConferenceApi>>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<List<ConferenceApi>>, response: Response<List<ConferenceApi>>) {
                val body = response.body()
                if (body == null) {
                    onError(IllegalStateException())
                } else {
                    onSuccess(body)
                }
            }
        })
    }

    private val api = Retrofit.Builder()
        .baseUrl("https://wrapapi.com/use/mariobodemann/")
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()
        .create(ConferencesApi::class.java)
}