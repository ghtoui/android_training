package jp.co.yumemi.droidtraining.usecases

import jp.co.yumemi.droidtraining.model.WeatherState
import jp.co.yumemi.droidtraining.repository.WeatherInfoRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherState: WeatherInfoRepository
){
    operator fun invoke(): StateFlow<WeatherState> {
        return weatherState.weatherState
    }
}
