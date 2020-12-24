package be.marche.www.event

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import be.marche.www.model.Event
import be.marche.www.event.repository.EventRepository

class EventViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val avaloirRepository: EventRepository,
) : ViewModel() {

    val allEvents: LiveData<List<Event>> = liveData {
        val superheroList = loadSuperheroes()
        emit(superheroList)
    }

    suspend fun loadSuperheroes(): List<Event> {
        return avaloirRepository.findAllEvents()
    }

}
