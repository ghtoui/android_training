package jp.co.yumemi.droidtraining.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.droidtraining.repository.WeatherInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val weatherInfoRepository: WeatherInfoRepository
) : ViewModel() {
    var weatherState = weatherInfoRepository.weatherState
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
        viewModelScope.launch(Dispatchers.Default) {
            weatherInfoRepository.fetchWeatherApi(onError = { _isShowErrorDialog.value = true })
            _isLoading.value = false
        }
    }
}
