package com.artemklymenko.myweatherapp.di

import android.content.Context
import com.artemklymenko.myweatherapp.data.WeatherRepositoryImpl
import com.artemklymenko.myweatherapp.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        @ApplicationContext context: Context
    ): WeatherRepository {
        return WeatherRepositoryImpl(context)
    }
}