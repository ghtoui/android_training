package jp.co.yumemi.droidtraining.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.model.WeatherInfoDataSource
import jp.co.yumemi.droidtraining.repository.WeatherInfoRepository
import jp.co.yumemi.droidtraining.repository.WeatherInfoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {
    @Provides
    @Singleton
    fun provideYumemiWeather(@ApplicationContext context: Context): YumemiWeather {
        return YumemiWeather(context)
    }

    @Provides
    @Singleton
    fun provideWeatherInfoDataSource(weatherApi: YumemiWeather): WeatherInfoDataSource {
        return WeatherInfoDataSource(weatherApi)
    }

    @Provides
    @Singleton
    fun provideWeatherInfoRepository(weatherInfoDataSource: WeatherInfoDataSource): WeatherInfoRepository {
        return WeatherInfoRepositoryImpl(weatherInfoDataSource)
    }
}
