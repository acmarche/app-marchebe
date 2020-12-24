package be.marche.www.api

import be.marche.www.model.Event
import be.marche.www.model.News
import retrofit2.http.GET

/**
 * todo nice response https://rahul9650ray.medium.com/dagger-hilt-retrofit-coroutines-9e8af89500ab
 */
interface MarcheBeService {

    @GET("marchebe/actualites")
    suspend fun findAllNews(
    ): List<News>

    @GET("agenda/evenements")
    suspend fun findAllEvents(
    ): List<Event>

}