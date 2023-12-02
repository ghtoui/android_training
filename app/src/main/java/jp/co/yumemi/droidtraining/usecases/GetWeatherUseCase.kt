package jp.co.yumemi.droidtraining.usecases

import jp.co.yumemi.droidtraining.model.WeatherInfoState
import jp.co.yumemi.droidtraining.repository.WeatherInfoRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherInfo: WeatherInfoRepository
) {
    operator fun invoke(): StateFlow<WeatherInfoState> {
        return weatherInfo.weatherInfoState
    }
}
