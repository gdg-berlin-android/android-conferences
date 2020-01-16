package de.berlindroid.androidconferences

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.berlindroid.androidconferences.ConferenceState.Failure
import de.berlindroid.androidconferences.ConferenceState.Success

sealed class ConferenceState {
    object Loading : ConferenceState()

    data class Failure(val throwable: Throwable) : ConferenceState()

    data class Success(val conferences: List<ConferenceApi>) : ConferenceState()
}

class ConferenceViewModel : ViewModel() {
    private val service = ConferenceService()
    val state: MutableLiveData<ConferenceState> = MutableLiveData()

    init {
        service
            .fetchConferences(
                onError = { throwable ->
                    state.postValue(Failure(throwable))
                },
                onSuccess = { body ->
                    state.postValue(Success(body))
                }
            )
    }

}