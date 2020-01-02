package de.berlindroid.androidconferences

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.berlindroid.androidconferences.ConferenceState.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var vm: ConferenceViewModel

    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProviders
            .of(this)
            .get(ConferenceViewModel::class.java)

        recycler = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)

        vm.state.observeForever { state ->
            when (state) {
                is Failure -> showError(state.throwable)
                is Success -> showConferences(state.conferences)
                Loading -> TODO()
            }.exhausive
        }
    }

    private fun showError(throwable: Throwable) {
        val message = "An error happened \uD83D\uDCA5."
        Log.d(TAG, message, throwable)

        Toast
            .makeText(
                this,
                message,
                Toast.LENGTH_LONG
            ).show()
    }

    private fun showConferences(list: List<ConferenceApi>) {
        recycler.adapter = ConferenceItemAdapter(list.toUi())
    }
}

val Any?.exhausive get() = Unit

private fun List<ConferenceApi>.toUi(): List<ConferenceUi> = this.map {
    ConferenceUi(it.name)
}
