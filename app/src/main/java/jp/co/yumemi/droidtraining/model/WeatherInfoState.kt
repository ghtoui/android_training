package jp.co.yumemi.droidtraining.model

import java.io.Serializable

// savedStateHandleにそのまま保存するには, Serializable or Parcelize にする必要がある
data class WeatherInfoState(
    val weatherSuccess: String = "",
    val area: String = "",
    val date: String = ""
) : Serializable

