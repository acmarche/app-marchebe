package be.marche.www.event.repository

import be.marche.www.api.MarcheBeService
import be.marche.www.database.EventDao
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val apiService: MarcheBeService,
    private val eventDao: EventDao
) {

    suspend fun findAllEvents() = apiService.findAllEvents()

}