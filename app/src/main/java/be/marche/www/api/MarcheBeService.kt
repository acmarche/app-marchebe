package be.marche.www.api

import be.marche.www.entity.Event
import be.marche.www.entity.News
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MarcheBeService {

    @GET("avaloirs/api/all")
    suspend fun getAllAvaloirs(
    ): List<News>

    //https://silex.marche.be/evenements/
    @GET("events")
     fun getAllEvents(): Deferred<List<Event>>

    //https://www.marche.be/mobile.php
    @GET("news")
     fun getAllActualites(): Deferred<List<News>>

}