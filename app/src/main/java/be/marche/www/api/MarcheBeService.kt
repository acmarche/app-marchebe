package be.marche.www.api

import be.marche.www.entity.Event
import be.marche.www.entity.News
import retrofit2.http.GET

interface MarcheBeService {

    @GET("avaloirs/api/all")
    suspend fun findAllNews(
    ): List<News>

    @GET("avaloirs/api/all")
    suspend fun findAllEvents(
    ): List<Event>

}