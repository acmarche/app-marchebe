package be.marche.www.event

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import be.marche.www.event.repository.EventRepository
import be.marche.www.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val eventRepository: EventRepository,
) : ViewModel() {

    val allEventFromRemote: LiveData<List<Event>> = liveData {
        val eventsList = loadEventFromRemote()
        emit(eventsList)
    }

    suspend private fun loadEventFromRemote(): List<Event> {
        return eventRepository.loadAllFromRemote()
    }

    fun findAllEventList(): List<Event> =
        eventRepository.findAll()

    fun findAllEvent(): LiveData<List<Event>> =
        liveData(Dispatchers.IO) {
            emit(eventRepository.findAll())
        }

    fun findById(newsId: Int): LiveData<Event> = liveData {
        emit(eventRepository.findById(newsId))
    }

    fun insertAll(events: List<Event>) {
        viewModelScope.launch {
            eventRepository.insertAll(events)
        }
    }

}
