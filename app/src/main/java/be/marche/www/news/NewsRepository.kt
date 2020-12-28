package be.marche.www.event.repository

import be.marche.www.api.MarcheBeService
import be.marche.www.database.NewsDao
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: MarcheBeService,
    private val newsDao: NewsDao
) {

    suspend fun findAllNews() = apiService.findAllNews()

}