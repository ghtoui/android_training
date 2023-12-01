package jp.co.yumemi.droidtraining.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.droidtraining.model.WeatherInfoState
import jp.co.yumemi.droidtraining.usecases.GetWeatherUseCase
import jp.co.yumemi.droidtraining.usecases.ReloadWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    getWeatherUseCase: GetWeatherUseCase,
    private val reloadWeatherUseCase: ReloadWeatherUseCase
) : ViewModel() {
    var weatherInfoState = getWeatherUseCase()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isShowErrorDialog = MutableStateFlow(false)
    val isShowErrorDialog: StateFlow<Boolean> = _isShowErrorDialog.asStateFlow()

    fun closeDialog() {
        _isShowErrorDialog.value = false
    }

    fun reloadData() {
        // 連打すると何回も実行されるので、trueの時は無視する
        if (_isLoading.value) {
            return
        }
        _isLoading.value = true
        val request = WeatherInfoState(
            area = "東京",
            date = "2020-04-01T12:00",
            minTemp = 0,
            maxTemp = 0,
            weather = "sunny"
        )
        val jsonRequest = Json.encodeToString(request)

        viewModelScope.launch(Dispatchers.Default) {
            reloadWeatherUseCase(jsonRequest, onError = { _isShowErrorDialog.value = true })
            _isLoading.value = false
        }
    }
}
