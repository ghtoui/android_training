package jp.co.yumemi.droidtraining.datasources

import jp.co.yumemi.api.UnknownException
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.model.WeatherState
import javax.inject.Inject

class WeatherInfoDataSource @Inject constructor(
    private val weatherApi: YumemiWeather
) {
    suspend fun fetchWeatherApi(onError: () -> Unit = {}): WeatherState? {
        var weatherState: WeatherState?
        try {
            weatherState = WeatherState(weatherApi.fetchWeatherAsync())
        } catch (error: UnknownException) {
            weatherState = null
            onError()
        }
        return weatherState
    }
}
