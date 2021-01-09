package be.marche.www.di

import android.app.Application
import be.marche.www.utils.Analytics
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAnalytics(application: Application) =
        FirebaseAnalytics.getInstance(application)

    @Provides
    @Singleton
    fun provideAnalytics(analytics: FirebaseAnalytics) =
        Analytics(analytics)

}