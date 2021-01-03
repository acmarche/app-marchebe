package be.marche.www.api

import be.marche.www.model.Event
import be.marche.www.model.News
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * todo nice response https://rahul9650ray.medium.com/dagger-hilt-retrofit-coroutines-9e8af89500ab
 */
interface MarcheBeService {

    @GET("marchebe/actualiites")
    suspend fun loadAllNews(
    ): List<News>

    @GET("agenda/evenements")
    suspend fun loadAllEvents(
    ): List<Event>

}