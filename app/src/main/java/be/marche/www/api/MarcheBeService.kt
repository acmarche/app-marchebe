package be.marche.www.api

import be.marche.www.entity.Event
import be.marche.www.entity.News
import retrofit2.http.GET

interface MarcheBeService {

    @GET("marchebe/actualites")
    suspend fun findAllNews(
    ): List<News>

    @GET("agenda/evenements")
    suspend fun findAllEvents(
    ): List<Event>

}