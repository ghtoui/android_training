package jp.co.yumemi.droidtraining.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.api.UnknownException
import jp.co.yumemi.api.YumemiWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val yumemiWeather: YumemiWeather
) : ViewModel() {
    // stateHandleにあるなら、そこから持ってくる。なければ、新しく作る。
    // これだけで生成から保存までの処理を勝手にしてくれるようになる
    // 保持できる型は決まりがあるので、それを守る必要がある
    var weatherState by savedStateHandle.saveable {
        mutableStateOf(WeatherState())
    }
        private set

    private suspend fun fetchApi(): WeatherState {
        return try {
            WeatherState(yumemiWeather.fetchWeatherAsync(), false)
        } catch (error: UnknownException) {
            WeatherState(null, true)
        }
    }

    fun closeDialog() {
        weatherState = WeatherState(
            weatherSuccess = weatherState.weatherSuccess,
            showErrorDialog = false,
            isLoading = false
        )
    }

    fun reloadData() {
        // 連打すると何回も実行されるので、trueの時は無視する
        if (weatherState.isLoading) {
            return
        }
        weatherState = WeatherState(
            weatherSuccess = weatherState.weatherSuccess,
            showErrorDialog = weatherState.showErrorDialog,
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.Default) {
            val state = fetchApi()
            weatherState = WeatherState(
                weatherSuccess = state.weatherSuccess,
                showErrorDialog = state.showErrorDialog,
                isLoading = false
            )
        }
    }
}
