package jp.co.yumemi.droidtraining.model

import android.util.Log
import androidx.lifecycle.ViewModel
import jp.co.yumemi.api.UnknownException
import jp.co.yumemi.api.YumemiWeather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel(
    private val yumemiWeather: YumemiWeather
) : ViewModel() {
    private val _weatherState = MutableStateFlow(WeatherState(null, false))
    val weatherState: StateFlow<WeatherState> = _weatherState.asStateFlow()

    init {
        reloadData()
    }

    private fun fetchApi(): WeatherState {
        return try {
            WeatherState(yumemiWeather.fetchThrowsWeather(), false)
        } catch (error: UnknownException) {
            WeatherState(null, true)
        }
    }

    fun closeDialog() {
        _weatherState.update { 
            it.copy(
                showErrorDialog = false
            )
        }
    }

    fun reloadData() {
        val state = fetchApi()
        Log.d("test", state.toString())
        _weatherState.update {
            it.copy(
                weatherSuccess = state.weatherSuccess,
                showErrorDialog = state.showErrorDialog
            )
        }
    }
}
