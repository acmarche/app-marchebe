package be.marche.www.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.marche.www.model.Event

@Dao
interface EventDao {

    @Query("SELECT * FROM event")
    fun findAllEvents(): List<Event>

    @Query("SELECT * FROM event WHERE id = :eventId")
    fun findEventById(eventId: Int): Event

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(news: List<Event>)

}