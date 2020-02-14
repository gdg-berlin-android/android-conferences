package de.berlindroid.androidconferences

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import de.berlindroid.androidconferences.ConferenceState.Failure
import de.berlindroid.androidconferences.ConferenceState.Loading
import de.berlindroid.androidconferences.ConferenceState.Success
import de.berlindroid.androidconferences.databinding.ActivityMainBinding

private const val MAIN_TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: ConferenceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProviders
            .of(this)
            .get(ConferenceViewModel::class.java)

        binding.recycler.layoutManager = LinearLayoutManager(this)

        vm
            .state
            .observeForever { state ->
                when (state) {
                    is Failure -> showError(state.throwable)
                    is Success -> showConferences(state.conferences)
                    is Loading -> showLoading()
                }.exhaustive
            }
    }

    private fun showError(throwable: Throwable) {
        val message = "An error happened \uD83D\uDCA5."
        Log.d(MAIN_TAG, message, throwable)

        Toast
            .makeText(
                this,
                message,
                Toast.LENGTH_LONG
            ).show()
    }

    private fun showConferences(list: List<ConferenceApi>) {
        binding
            .recycler
            .adapter = ConferenceItemAdapter(list.toUi())
    }

    private fun showLoading() {

    }
}

val Any?.exhaustive get() = Unit

private fun List<ConferenceApi>.toUi(): List<ConferenceUi> = this.map {
    ConferenceUi(
        title = it.name,
        link = it.link,
        place = it.place,
        date = it.date,
        cfpLink = it.cfp?.url
    )
}
