package be.marche.www.event

import android.app.Application
import androidx.compose.runtime.emit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import be.marche.www.entity.Event
import be.marche.www.event.repository.EventRepository

class EventViewModel(
    application: Application,
    private val avaloirRepository: EventRepository,
) : AndroidViewModel(application) {

    fun findAllEvents(): LiveData<List<Event>> = liveData {
        emit(avaloirRepository.findAllEvents())
    }

}