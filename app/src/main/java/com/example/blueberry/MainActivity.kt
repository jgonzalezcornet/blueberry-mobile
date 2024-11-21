package com.example.blueberry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.blueberry.ui.AdaptiveApp
import com.example.blueberry.ui.theme.BlueberryTheme
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueberryTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    RepeatingPatternImage(painter = painterResource(id = R.drawable.background))
                }
                AdaptiveApp()
            }
        }
    }
}

@Composable
fun RepeatingPatternImage(painter: Painter) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp // screen width in dp
    val screenHeight = configuration.screenHeightDp // screen height in dp
    val columnWidth = 300.dp // Width of each item in the grid
    val columns = calculateNumberOfColumns(screenWidth, columnWidth)

    // Calculate item size dynamically for the grid to fit without white space
    val itemSize = calculateTileSize(screenWidth, columns)

    val rows = calculateNumberOfRows(screenHeight, itemSize) // Ensure full height coverage

    // Calculate the number of items needed to fill the screen
    val totalItems = columns * rows

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns), // Dynamically set the number of columns
        modifier = Modifier.fillMaxSize()
    ) {
        items(totalItems) { // Ensure you have enough items to cover the entire screen
            Image(
                painter = painter,
                contentDescription = "Pattern Tile",
                modifier = Modifier
                    .size(itemSize) // Dynamically sized tile
                    .graphicsLayer(alpha = 0.5f) // Apply 70% opacity
            )
        }
    }
}

// Function to calculate the number of columns based on screen width and item size
@Composable
fun calculateNumberOfColumns(screenWidthDp: Int, itemWidth: Dp): Int {
    val screenWidthPx = with(LocalDensity.current) { screenWidthDp.dp.toPx() } // Convert dp to px
    val itemWidthPx = with(LocalDensity.current) { itemWidth.toPx() } // Convert item width to px
    return (screenWidthPx / itemWidthPx).toInt() + 1 // Calculate the number of columns
}

// Function to calculate the number of rows based on screen height and item size
@Composable
fun calculateNumberOfRows(screenHeightDp: Int, itemWidth: Dp): Int {
    val screenHeightPx = with(LocalDensity.current) { screenHeightDp.dp.toPx() } // Convert dp to px
    val itemHeightPx = with(LocalDensity.current) { itemWidth.toPx() } // Assume square tiles
    return (screenHeightPx / itemHeightPx).toInt() + 1 // +1 to ensure coverage, even if it's fractional
}

// Function to calculate dynamic tile size for even coverage
@Composable
fun calculateTileSize(screenWidthDp: Int, columns: Int): Dp {
    val screenWidthPx = with(LocalDensity.current) { screenWidthDp.dp.toPx() } // Convert dp to px
    return with(LocalDensity.current) { (screenWidthPx / columns).toDp() } // Calculate tile size
}

@PreviewScreenSizes
@Composable
fun MainActivityPreview() {
    BlueberryTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            RepeatingPatternImage(painter = painterResource(id = R.drawable.background))
        }
        AdaptiveApp()
    }
}
