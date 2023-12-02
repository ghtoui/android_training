package jp.co.yumemi.droidtraining.usecases

import jp.co.yumemi.droidtraining.repository.WeatherInfoRepository
import javax.inject.Inject

class NextWeatherInfoUseCase @Inject constructor(
    private val weatherInfoRepository: WeatherInfoRepository
) {
    operator suspend fun invoke(onError: () -> Unit) {
        weatherInfoRepository.nextCityId()
        return weatherInfoRepository.fetchWeatherApi(onError)
    }
}
