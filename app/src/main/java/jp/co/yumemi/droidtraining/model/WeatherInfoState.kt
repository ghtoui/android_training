package jp.co.yumemi.droidtraining.model

import kotlinx.serialization.Serializable

// savedStateHandleにそのまま保存するには, Serializable or Parcelize にする必要がある
@Serializable
data class WeatherInfoState(
    val weather: String,
    val maxTemp: Int,
    val minTemp: Int,
    val area: String,
    val date: String
)

