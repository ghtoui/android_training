package jp.co.yumemi.droidtraining.repository

import jp.co.yumemi.droidtraining.model.WeatherInfoDataSource
import jp.co.yumemi.droidtraining.model.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

interface WeatherInfoRepository {
    val weatherState: StateFlow<WeatherState>
    suspend fun fetchWeatherApi(onError: () -> Unit)
}

class WeatherInfoRepositoryImpl @Inject constructor(
    private val weatherInfoDataSource: WeatherInfoDataSource
) : WeatherInfoRepository {
    private val _weatherState = MutableStateFlow(WeatherState())
    override val weatherState = _weatherState.asStateFlow()

    override suspend fun fetchWeatherApi(onError: () -> Unit) {
        val updateWeatherState = weatherInfoDataSource.fetchWeatherApi(onError = onError)
        if (updateWeatherState != null) {
            _weatherState.value = updateWeatherState
        }
    }
}
