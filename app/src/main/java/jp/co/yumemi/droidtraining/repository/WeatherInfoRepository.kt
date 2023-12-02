package jp.co.yumemi.droidtraining.repository

import jp.co.yumemi.droidtraining.datasources.WeatherInfoDataSource
import jp.co.yumemi.droidtraining.model.WeatherInfoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

interface WeatherInfoRepository {
    val weatherInfoState: StateFlow<WeatherInfoState>
    suspend fun fetchWeatherApi(jsonRequest: String, onError: () -> Unit)
}

class WeatherInfoRepositoryImpl @Inject constructor(
    private val weatherInfoDataSource: WeatherInfoDataSource
) : WeatherInfoRepository {
    private val _weatherInfoState: MutableStateFlow<WeatherInfoState> = MutableStateFlow(
        WeatherInfoState(
            weather = "",
            area = "",
            date = "",
            minTemp = 0,
            maxTemp = 0
        )
    )
    override val weatherInfoState = _weatherInfoState.asStateFlow()

    override suspend fun fetchWeatherApi(jsonRequest: String, onError: () -> Unit) {
        val updateWeatherState = weatherInfoDataSource.fetchWeatherApi(jsonRequest, onError = onError)
        if (updateWeatherState != null) {
            _weatherInfoState.value = updateWeatherState
        }
    }
}
