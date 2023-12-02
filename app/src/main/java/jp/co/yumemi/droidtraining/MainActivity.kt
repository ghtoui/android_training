package jp.co.yumemi.droidtraining

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import jp.co.yumemi.droidtraining.ui.HomeScreen
import jp.co.yumemi.droidtraining.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HomeScreen()
            }
        }
    }
}
