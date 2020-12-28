package be.marche.www.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.marche.www.model.Event
import be.marche.www.model.News

@Dao
interface EventDao {

    @Query("SELECT * FROM news")
    fun getAllNews(): List<News>

    @Query("SELECT * FROM event")
    fun getAllEvents(): List<Event>

    @Query("SELECT * FROM news WHERE id = :newsId")
    fun getNewsById(newsId: Int): News

    @Query("SELECT * FROM news WHERE id = :eventId")
    fun getEventsById(eventId: Int): News

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<News>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(news: List<Event>)

}