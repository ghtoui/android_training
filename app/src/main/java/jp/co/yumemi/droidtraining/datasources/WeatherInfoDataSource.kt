package jp.co.yumemi.droidtraining.datasources

import jp.co.yumemi.api.UnknownException
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.model.WeatherInfoState
import javax.inject.Inject

class WeatherInfoDataSource @Inject constructor(
    private val weatherApi: YumemiWeather
) {
    suspend fun fetchWeatherApi(onError: () -> Unit = {}): WeatherInfoState? {
        var weatherInfoState: WeatherInfoState?
        try {
            weatherInfoState = WeatherInfoState(weatherApi.fetchWeatherAsync())
        } catch (error: UnknownException) {
            weatherInfoState = null
            onError()
        }
        return weatherInfoState
    }
}
