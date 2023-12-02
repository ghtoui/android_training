package jp.co.yumemi.droidtraining.usecases

import jp.co.yumemi.droidtraining.repository.WeatherInfoRepository
import javax.inject.Inject

class ReloadWeatherUseCase @Inject constructor(
    private val weatherInfoRepository: WeatherInfoRepository
) {
    suspend operator fun invoke(jsonRequest: String, onError: () -> Unit) {
        return weatherInfoRepository.fetchWeatherApi(jsonRequest, onError)
    }
}
