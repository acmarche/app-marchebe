package be.marche.www.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import be.marche.www.model.Event
import be.marche.www.model.News

const val DATABASE_NAME = "marchebe"

@Database(
    entities = [News::class, Event::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun eventDao(): EventDao

    companion object {

        val DATABASE_NAME = "marchebe"

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}