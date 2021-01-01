package be.marche.www.di

import be.marche.www.BuildConfig
import be.marche.www.Constants
import be.marche.www.api.BasicAuthInterceptor
import be.marche.www.api.BottinService
import be.marche.www.api.MarcheBeService
import be.marche.www.bottin.model.BottinSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideBaseUrl() = Constants.MARCHE_BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .addInterceptor(BasicAuthInterceptor(BottinSettings.USER, BottinSettings.PASSWORD))
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(MarcheBeService::class.java)

    @Provides
    @Singleton
    fun provideBottinService(retrofit: Retrofit) = retrofit.create(BottinService::class.java)

}