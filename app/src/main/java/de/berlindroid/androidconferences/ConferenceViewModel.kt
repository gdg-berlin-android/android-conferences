package de.berlindroid.androidconferences

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.berlindroid.androidconferences.ConferenceState.Failure
import de.berlindroid.androidconferences.ConferenceState.Loading
import de.berlindroid.androidconferences.ConferenceState.Success
import kotlinx.coroutines.launch

sealed class ConferenceState {
    object Loading : ConferenceState()

    data class Failure(val throwable: Throwable) : ConferenceState()

    data class Success(val conferences: List<ConferenceApi>) : ConferenceState()
}

class ConferenceViewModel(private val service: ConferenceService = ConferenceService()) : ViewModel() {
    val state: MutableLiveData<ConferenceState> = MutableLiveData(Loading)

    fun getData() {
        viewModelScope.launch {
            try {
                state.postValue(
                    Success(
                        service.fetchConferences()
                    )
                )
            } catch (th: Throwable) {
                state.postValue(Failure(th))
            }
        }
    }
}
