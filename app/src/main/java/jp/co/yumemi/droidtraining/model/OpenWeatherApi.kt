package jp.co.yumemi.droidtraining.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("data/2.5/weather")
    suspend fun fetchWeatherInfo(
        @Query("id") cityId: Int,
    ): Response<WeatherInfo>
}
