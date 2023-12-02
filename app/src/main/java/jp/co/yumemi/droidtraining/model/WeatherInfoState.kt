package jp.co.yumemi.droidtraining.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// savedStateHandleにそのまま保存するには, Serializable or Parcelize にする必要がある
@JsonClass(generateAdapter = true)
data class WeatherInfoState(
    val weather: WeatherType,
    val maxTemp: Double,
    val minTemp: Double,
    val area: String,
)

@JsonClass(generateAdapter = true)
data class WeatherInfo(
    val weather: List<Weather>,
    val main: Main,
    val name: String
)

@JsonClass(generateAdapter = true)
data class Weather(
    val id: Int,
    @Json(name = "main")
    val weather: WeatherType,
    val description: String,
    val icon: String
)

enum class WeatherType {
    Rain,
    Thunderstorm,
    Drizzle,
    Snow,
    Clear,
    Clouds,
    Mist,
    Smoke,
    Haze,
    Dust,
    Fog,
    Sand,
    Ash,
    Squall,
    Tornado
}

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "temp_min")
    val minTemp: Double,
    @Json(name = "temp_max")
    val maxTemp: Double,
    val temp: Double,
)
