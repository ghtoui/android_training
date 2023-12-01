package jp.co.yumemi.droidtraining.datasources

import jp.co.yumemi.api.UnknownException
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.model.WeatherInfoState
import kotlinx.serialization.json.Json
import javax.inject.Inject

class WeatherInfoDataSource @Inject constructor(
    private val weatherApi: YumemiWeather
) {
    suspend fun fetchWeatherApi(jsonRequest: String, onError: () -> Unit = {}): WeatherInfoState? {
        var weatherInfoState: WeatherInfoState?
        try {
            weatherInfoState = Json.decodeFromString(weatherApi.fetchJsonWeatherAsync(jsonRequest))
        } catch (error: UnknownException) {
            weatherInfoState = null
            onError()
        }
        return weatherInfoState
    }
}
