package com.example.blueberry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.blueberry.ui.login.LoginScreen
import com.example.blueberry.ui.theme.BlueberryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlueberryTheme {
                LoginScreen()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun MainActivityPreview() {
    BlueberryTheme {
        LoginScreen()
    }
}