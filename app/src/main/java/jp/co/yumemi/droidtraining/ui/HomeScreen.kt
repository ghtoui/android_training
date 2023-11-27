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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.co.yumemi.droidtraining.R

@Composable
fun HomeScreen() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Box(
        contentAlignment = Alignment.Center
        ) {
        Column(
            modifier = Modifier
                .width(screenWidth / 2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            WeatherInfo()
            ReloadButton(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun WeatherInfo() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
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
fun ReloadButton(modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(top = 80.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top
        ) {
        Button(
            onClick = {},
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

        Button(
            onClick = {},
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
@Preview
fun HomeScreenPreview() {
    Box(Modifier.background(Color.White)) {
        HomeScreen()
    }
}

@Composable
@Preview
fun WeatherInfoPreview() {
    Box(Modifier.background(Color.White)) {
        WeatherInfo()
    }
}

@Composable
@Preview
fun ReloadButtonPreview() {
    Box(Modifier.background(Color.White)) {
        ReloadButton(modifier = Modifier)
    }
}
