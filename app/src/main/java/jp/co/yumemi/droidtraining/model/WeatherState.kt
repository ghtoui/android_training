package jp.co.yumemi.droidtraining.model

import java.io.Serializable

// savedStateHandleにそのまま保存するには, Serializable or Parcelize にする必要がある
data class WeatherState(
    val weatherSuccess: String? = null,
    var showErrorDialog: Boolean = false
) : Serializable
