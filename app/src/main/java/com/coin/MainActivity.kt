package com.coin

import CoinApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.coin.ui.theme.CoinClientAndroidTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinClientAndroidTheme {
                val windowSize = calculateWindowSizeClass(this)

                CoinApp(
                    windowSize = windowSize
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoinClientAndroidTheme {
        CoinApp(
            windowSize = WindowSizeClass.calculateFromSize(DpSize(400.dp, 900.dp))
        )
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 700, heightDp = 500)
@Composable
fun GreetingPreviewTablet() {
    CoinClientAndroidTheme {
        CoinApp(
            windowSize = WindowSizeClass.calculateFromSize(DpSize(700.dp, 500.dp))
        )
    }
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 500, heightDp = 700)
@Composable
fun GreetingPreviewTabletPortrait() {
    CoinClientAndroidTheme {
        CoinApp(
            windowSize = WindowSizeClass.calculateFromSize(DpSize(500.dp, 700.dp))
        )
    }
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 1100, heightDp = 600)
@Composable
fun GreetingPreviewDesktop() {
    CoinClientAndroidTheme {
        CoinApp(
            windowSize = WindowSizeClass.calculateFromSize(DpSize(1100.dp, 600.dp))
        )
    }
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 600, heightDp = 1100)
@Composable
fun GreetingPreviewDesktopPortrait() {
    CoinClientAndroidTheme {
        CoinApp(
            windowSize = WindowSizeClass.calculateFromSize(DpSize(600.dp, 1100.dp))
        )
    }
}