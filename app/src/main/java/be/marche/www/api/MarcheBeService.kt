package be.marche.www.api

import be.marche.www.model.Event
import be.marche.www.model.News
import retrofit2.http.GET

interface MarcheBeService {

    @GET("marchebe/actualites")
    suspend fun findAllNews(
    ): List<News>

    @GET("agenda/evenements")
    suspend fun findAllEvents(
    ): List<Event>

}