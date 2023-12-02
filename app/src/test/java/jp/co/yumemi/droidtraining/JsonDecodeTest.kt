package jp.co.yumemi.droidtraining

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import jp.co.yumemi.droidtraining.model.WeatherInfo
import jp.co.yumemi.droidtraining.model.WeatherInfoState
import jp.co.yumemi.droidtraining.model.WeatherType
import org.junit.Test

class JsonDecodeTest {
    private val moshi = Moshi.Builder()
        .build()

    @Test
    fun decodeSuccess() {
        val json = moshi.adapter(WeatherInfoState::class.java).toJson(
            WeatherInfoState(
                area = "東京",
                maxTemp = 25.0,
                minTemp = 15.0,
                weather = WeatherType.Clear
            )
        )
        val isSuccess = try {
            moshi.adapter(WeatherInfoState::class.java).fromJson(json)
            true
        } catch (error: Exception) {
            false
        }
        assertThat(isSuccess).isEqualTo(true)
    }
    @Test
    fun decodeNotSuccess() {
        val json = moshi.adapter(WeatherInfoState::class.java).toJson(
            WeatherInfoState(
                area = "東京",
                maxTemp = 25.0,
                minTemp = 15.0,
                weather = WeatherType.Clear
            )
        )

        val isNotSuccess = try {
            moshi.adapter(WeatherInfo::class.java).fromJson(json)
            true
        } catch (error: Exception) {
            false
        }
        assertThat(isNotSuccess).isNotEqualTo(true)
    }
}
