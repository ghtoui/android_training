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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.co.yumemi.droidtraining.R
import jp.co.yumemi.droidtraining.model.WeatherInfoState
import jp.co.yumemi.droidtraining.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel()
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val weatherInfoState = viewModel.weatherInfoState.collectAsState()
    val isShowErrorDialog = viewModel.isShowErrorDialog.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()

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
            WeatherInfo(weatherInfo = weatherInfoState.value)
            ActionButtons(
                reloadClick = viewModel::reloadData,
                nextClick = { },
                modifier = Modifier.weight(1f)
            )
        }
    }
    ShowErrorDialog(
        isShow = isShowErrorDialog.value,
        cancelClick = viewModel::closeDialog,
        reloadClick = {
            viewModel.closeDialog()
            viewModel.reloadData()
        }
    )
    Loading(
        isLoading = isLoading.value
    )
}

@Composable
fun WeatherInfo(
    weatherInfo: WeatherInfoState
) {
    val imageId = when (weatherInfo.weather) {
        stringResource(id = R.string.sunny) -> painterResource(id = R.drawable.sunny)
        stringResource(id = R.string.cloudy) -> painterResource(id = R.drawable.cloudy)
        stringResource(id = R.string.rainy) -> painterResource(id = R.drawable.rainy)
        stringResource(id = R.string.snow) -> painterResource(id = R.drawable.snow)
        else -> painterResource(id = R.drawable.ic_launcher_foreground)
    }
    Column {
        Text(
            text = weatherInfo.area,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = imageId,
            contentDescription = weatherInfo.weather,
            modifier = Modifier
                .aspectRatio(1f / 1f)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${weatherInfo.minTemp} ℃",
                textAlign = TextAlign.Center,
                color = Color.Blue,
                modifier = Modifier
                    .weight(1f)
            )
            Text(
                text = "${weatherInfo.minTemp} ℃",
                textAlign = TextAlign.Center,
                color = Color.Red,
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
fun Loading(
    isLoading: Boolean
) {
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WeatherInfoPreview() {
    Box(Modifier.fillMaxSize().background(Color.White)) {
        WeatherInfo(
            weatherInfo = WeatherInfoState(
                weather = "sunny",
                maxTemp = 100,
                minTemp = 0,
                area = "東京",
                date = "2020"
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DialogPreview() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ShowErrorDialog(
            isShow = true,
            cancelClick = { /*TODO*/ },
            reloadClick = { /*TODO*/ }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoadingPreview() {
    Loading(isLoading = true)
}
