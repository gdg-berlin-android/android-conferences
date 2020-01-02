package de.berlindroid.androidconferences

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)

        Retrofit
            .Builder()
            .baseUrl("https://wrapapi.com/use/mariobodemann/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            .create(ConferencesApi::class.java)
            .listRepos(
                "0.0.3",
                "7UF1tvyFxs4TXQDYq4btuIIfIuHvbSdz"
            ).enqueue(
                object : Callback<List<ConferenceApi>> {
                    override fun onFailure(call: Call<List<ConferenceApi>>, t: Throwable) {
                        showError()
                    }

                    override fun onResponse(call: Call<List<ConferenceApi>>, response: Response<List<ConferenceApi>>) {
                        val body = response.body()
                        if (body == null) {
                            showError()
                        } else {
                            recycler
                                .adapter =
                                ConferenceItemAdapter(
                                    body
                                        .toUi()
                                )
                        }

                    }
                }
            )
    }

    private fun showError() {
        val message = "An error happened \uD83D\uDCA5."
        Log.d(TAG, message)

        Toast
            .makeText(
                this,
                message,
                Toast.LENGTH_LONG
            ).show()
    }
}

private fun List<ConferenceApi>.toUi(): List<ConferenceUi> = this.map {
    ConferenceUi(it.name)
}


