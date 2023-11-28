package jp.co.yumemi.droidtraining.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.co.yumemi.api.UnknownException
import jp.co.yumemi.api.YumemiWeather
import jp.co.yumemi.droidtraining.R
import jp.co.yumemi.droidtraining.model.WeatherState

@Composable
fun HomeScreen() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val context = LocalContext.current
    val yumemiWeather by remember { mutableStateOf(YumemiWeather(context = context)) }
    var weatherData by remember { mutableStateOf(fetchMethod(yumemiWeather)) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(screenWidth / 2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            WeatherInfo(weather = weatherData)
            ActionButtons(
                reloadClick = { weatherData = fetchMethod(yumemiWeather) },
                nextClick = { },
                modifier = Modifier.weight(1f)
            )
        }
    }
    ShowErrorDialog(
        isShow = weatherData.showErrorDialog,
        cancelClick = { weatherData = WeatherState(weatherData.weatherSuccess, false) },
        reloadClick = { weatherData = fetchMethod(yumemiWeather) }
    )
}

// api取得して、返り値に合わせて処理
private fun fetchMethod(yumemiWeather: YumemiWeather): WeatherState {
    return try {
        WeatherState(yumemiWeather.fetchThrowsWeather(), false)
    } catch (error: UnknownException) {
        WeatherState(null, true)
    }
}

@Composable
fun WeatherInfo(weather: WeatherState) {
    val imageId = when (weather.weatherSuccess) {
        stringResource(id = R.string.sunny) -> painterResource(id = R.drawable.sunny)
        stringResource(id = R.string.cloudy) -> painterResource(id = R.drawable.cloudy)
        stringResource(id = R.string.rainy) -> painterResource(id = R.drawable.rainy)
        stringResource(id = R.string.snow) -> painterResource(id = R.drawable.snow)
        else -> painterResource(id = R.drawable.ic_launcher_foreground)
    }
    Column {
        Image(
            painter = imageId,
            contentDescription = weather.weatherSuccess,
            modifier = Modifier
                .aspectRatio(1f / 1f)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "text",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
            )
            Text(
                "text",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun ActionButtons(
    reloadClick: () -> Unit,
    nextClick: () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .padding(top = 80.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top
    ) {
        TextButton(
            onClick = { reloadClick() },
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.Black,
                disabledContentColor = Color.LightGray
            ),
            modifier = Modifier
                .background(Color.Black)
        ) {
            Text(
                "RELOAD",
                color = Color.White,
                fontSize = 12.sp
            )
        }

        TextButton(
            onClick = { nextClick() },
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.Black,
                disabledContentColor = Color.LightGray
            ),
            modifier = Modifier
                .background(Color.Black)
        ) {
            Text(
                "NEXT",
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun ShowErrorDialog(
    isShow: Boolean,
    cancelClick: () -> Unit,
    reloadClick: () -> Unit
) {
    if (isShow) {
        AlertDialog(
            title = {
                Text("Error")
            },
            text = {
                Text("エラーが発生しました")
            },
            onDismissRequest = { },
            confirmButton = {
                TextButton(onClick = { reloadClick() }) {
                    Text("reload")
                }
            },
            dismissButton = {
                TextButton(onClick = { cancelClick() }) {
                    Text("cancel")
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    Box(Modifier.background(Color.White)) {
        HomeScreen()
    }
}

@Composable
@Preview(showBackground = true)
fun DialogPreview() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        ShowErrorDialog(
            isShow = true,
            cancelClick = { /*TODO*/ },
            reloadClick = { /*TODO*/ }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ReloadButtonPreview() {
    Box(Modifier.background(Color.White)) {
        ActionButtons(
            reloadClick = { },
            nextClick = { },
            modifier = Modifier
        )
    }
}
