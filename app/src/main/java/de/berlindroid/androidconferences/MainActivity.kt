package de.berlindroid.androidconferences

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        ConferenceService()
            .fetchConferences(
                onError = { showError() },
                onSuccess = { body ->
                    recycler.adapter = ConferenceItemAdapter(body.toUi())
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