package jp.co.yumemi.droidtraining.datasources

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
                cityId = cityId
            )
            weatherInfo = response.body() ?: throw IllegalStateException("body is null")
        } catch (error: Exception) {
            weatherInfo = null
            onError()
        }
        return weatherInfo
    }
}
