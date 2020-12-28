package be.marche.www.di

import android.app.Application
import androidx.room.Room
import be.marche.www.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "database"
        ).build()

    @Provides
    @Singleton
    fun provideEventDao(appDatabase: AppDatabase) = appDatabase.eventDao()

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase) = appDatabase.newsDao()

}
