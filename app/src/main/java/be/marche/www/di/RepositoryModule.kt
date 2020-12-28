
package be.marche.www.di

import be.marche.www.api.MarcheBeService
import be.marche.www.database.EventDao
import be.marche.www.database.NewsDao
import be.marche.www.event.repository.EventRepository
import be.marche.www.event.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideEventRepository(apiService: MarcheBeService, eventDao: EventDao) = EventRepository(apiService, eventDao)

    @Provides
    @ActivityRetainedScoped
    fun provideNewsRepository(apiService: MarcheBeService, newsDao: NewsDao) = NewsRepository(apiService,newsDao)

}
