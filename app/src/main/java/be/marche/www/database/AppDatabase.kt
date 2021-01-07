package be.marche.www.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import be.marche.bottin.model.Categorie
import be.marche.bottin.model.Fiche
import be.marche.www.bottin.model.Classement
import be.marche.www.model.Event
import be.marche.www.model.News

@Database(
    entities = [News::class, Event::class, Fiche::class, Categorie::class, Classement::class],
    version = 12
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun eventDao(): EventDao
    abstract fun bottinDao(): BottinDao

    companion object {

        const val DATABASE_NAME = "marchebe"

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}