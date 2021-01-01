package be.marche.www.di

import android.app.Application
import be.marche.www.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) = AppDatabase.buildDatabase(application)

    @Provides
    @Singleton
    fun provideEventDao(appDatabase: AppDatabase) = appDatabase.eventDao()

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase) = appDatabase.newsDao()

    @Provides
    @Singleton
    fun provideBottinDao(appDatabase: AppDatabase) = appDatabase.bottinDao()

}
