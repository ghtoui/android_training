package jp.co.yumemi.droidtraining.datasources

import android.util.Log
import jp.co.yumemi.droidtraining.OpenWeatherAPIKey
import jp.co.yumemi.droidtraining.model.OpenWeatherService
import jp.co.yumemi.droidtraining.model.WeatherInfo
import javax.inject.Inject

class WeatherInfoDataSource @Inject constructor(
    private val openWeatherApi: OpenWeatherService
) {
    suspend fun fetchWeatherApi(cityId: Int, onError: () -> Unit = {}): WeatherInfo? {
        var weatherInfo: WeatherInfo?

        try {
            val response = openWeatherApi.fetchWeatherInfo(
                apiKey = OpenWeatherAPIKey.getApiKey(),
                cityId = cityId,
                langage = "ja",
                unit = "metric"
            )
            Log.d("test", response.toString())
            Log.d("test", response.body().toString())
            weatherInfo = response.body() ?: throw IllegalStateException("body is null")
        } catch (error: Exception) {
            weatherInfo = null
            Log.d("test", error.toString())
            onError()
        }
        return weatherInfo
    }
}
