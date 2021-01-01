package be.marche.www.di

import be.marche.www.api.BottinService
import be.marche.www.api.MarcheBeService
import be.marche.www.bottin.repository.CategoryRepository
import be.marche.www.bottin.repository.ClassementRepository
import be.marche.www.bottin.repository.FicheRepository
import be.marche.www.database.BottinDao
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
    fun provideEventRepository(apiService: MarcheBeService, eventDao: EventDao) =
        EventRepository(apiService, eventDao)

    @Provides
    @ActivityRetainedScoped
    fun provideNewsRepository(apiService: MarcheBeService, newsDao: NewsDao) =
        NewsRepository(apiService, newsDao)

    @Provides
    @ActivityRetainedScoped
    fun provideFicheRepository(apiService: BottinService, bottinDao: BottinDao) =
        FicheRepository(apiService, bottinDao)

    @Provides
    @ActivityRetainedScoped
    fun provideCtegoryRepository(apiService: BottinService, bottinDao: BottinDao) =
        CategoryRepository(apiService, bottinDao)

    @Provides
    @ActivityRetainedScoped
    fun provideClassementRepository(apiService: BottinService, bottinDao: BottinDao) =
        ClassementRepository(apiService, bottinDao)

}
