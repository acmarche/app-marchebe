package be.marche.www.event.repository

import be.marche.www.api.MarcheBeService
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val apiService: MarcheBeService
) {

    suspend fun findAllEvents() = apiService.findAllEvents()

}