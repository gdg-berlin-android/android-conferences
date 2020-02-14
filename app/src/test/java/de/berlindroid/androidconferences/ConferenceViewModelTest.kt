package de.berlindroid.androidconferences

import de.berlindroid.androidconferences.ConferenceState.Success
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Test

@ExperimentalCoroutinesApi
class ConferenceViewModelTest {

    val dispatcher = TestCoroutineDispatcher()
    val service = mockk<ConferenceService>()

    @Test
    fun getState() = runBlockingTest(dispatcher) {
        Dispatchers.setMain(dispatcher)

        coEvery { service.fetchConferences() }.returns(listOf())

        val subject = ConferenceViewModel(service)

        subject.getData()

        assertThat(subject.state.value, IsEqual<ConferenceState>(Success(listOf())))
    }
}
