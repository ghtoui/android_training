package jp.co.yumemi.droidtraining.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.OpenWeatherAPIKey
import jp.co.yumemi.droidtraining.datasources.WeatherInfoDataSource
import jp.co.yumemi.droidtraining.model.OpenWeatherService
import jp.co.yumemi.droidtraining.repository.WeatherInfoRepository
import jp.co.yumemi.droidtraining.repository.WeatherInfoRepositoryImpl
import jp.co.yumemi.droidtraining.usecases.GetWeatherUseCase
import jp.co.yumemi.droidtraining.usecases.ReloadWeatherUseCase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(OpenWeatherAPIKey.getBaseUrl())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): OpenWeatherService {
        return retrofit.create(OpenWeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherInfoRepository(weatherInfoDataSource: WeatherInfoDataSource): WeatherInfoRepository {
        return WeatherInfoRepositoryImpl(weatherInfoDataSource)
    }

    @Provides
    @Singleton
    fun provideGetWeatherUseCase(weatherInfoRepository: WeatherInfoRepository): GetWeatherUseCase {
        return GetWeatherUseCase(weatherInfoRepository)
    }

    @Provides
    @Singleton
    fun provideReloadWeatherUseCase(weatherInfoRepository: WeatherInfoRepository): ReloadWeatherUseCase {
        return ReloadWeatherUseCase(weatherInfoRepository)
    }
}
