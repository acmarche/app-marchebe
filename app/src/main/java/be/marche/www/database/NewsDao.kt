package be.marche.www.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.marche.www.model.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun findAllNews(): List<News>

    @Query("SELECT * FROM news WHERE id = :newsId")
    fun findNewsById(newsId: Int): News

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<News>)

}