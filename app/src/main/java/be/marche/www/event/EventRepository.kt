package be.marche.www.event.repository

import be.marche.www.api.MarcheBeService
import be.marche.www.database.EventDao
import be.marche.www.model.Event
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val apiService: MarcheBeService,
    private val eventDao: EventDao
) {

    suspend fun loadAllFromRemote() = apiService.loadAllEvents()

    fun findAll() = eventDao.findAllEvents()

    suspend fun insertAll(events: List<Event>) {
        eventDao.insertEvents(events)
    }

    suspend fun findById(eventId: Int): Event {
        return eventDao.findById(eventId)
    }
}