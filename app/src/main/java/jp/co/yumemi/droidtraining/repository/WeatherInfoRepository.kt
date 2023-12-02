package jp.co.yumemi.droidtraining.repository

import jp.co.yumemi.droidtraining.datasources.WeatherInfoDataSource
import jp.co.yumemi.droidtraining.model.City
import jp.co.yumemi.droidtraining.model.WeatherInfoState
import jp.co.yumemi.droidtraining.model.WeatherType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

interface WeatherInfoRepository {
    val weatherInfoState: StateFlow<WeatherInfoState>
    var cityId: Int
    suspend fun fetchWeatherApi(onError: () -> Unit)
    fun nextCityId()
}

class WeatherInfoRepositoryImpl @Inject constructor(
    private val weatherInfoDataSource: WeatherInfoDataSource
) : WeatherInfoRepository {
    private val _weatherInfoState: MutableStateFlow<WeatherInfoState> = MutableStateFlow(
        WeatherInfoState(
            weather = WeatherType.Clear,
            area = "",
            minTemp = 0.0,
            maxTemp = 0.0
        )
    )
    override val weatherInfoState = _weatherInfoState.asStateFlow()

    override var cityId: Int = City.getCityId()

    override suspend fun fetchWeatherApi(onError: () -> Unit) {
        val weatherInfo = weatherInfoDataSource.fetchWeatherApi(cityId, onError = onError)
        if (weatherInfo != null) {
            _weatherInfoState.value = WeatherInfoState(
                weather = weatherInfo.weather.first().weather,
                maxTemp = weatherInfo.main.maxTemp,
                minTemp = weatherInfo.main.minTemp,
                area = weatherInfo.name
            )
        }
    }

    override fun nextCityId() {
        cityId = City.getCityId()
    }
}
