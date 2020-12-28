package be.marche.www.event.repository

import be.marche.www.api.MarcheBeService
import be.marche.www.database.NewsDao
import be.marche.www.model.News
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: MarcheBeService,
    private val newsDao: NewsDao
) {

    suspend fun findAllNewsFromRemote() = apiService.findAllNews()

    suspend fun findAllNews() = newsDao.getAllNews()

    suspend fun insertNews(news: List<News>) {
        newsDao.insertNews(news)
    }
}