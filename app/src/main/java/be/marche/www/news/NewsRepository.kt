package be.marche.www.event.repository

import be.marche.www.api.MarcheBeService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: MarcheBeService
) {

    suspend fun findAllNews() = apiService.findAllNews()

}