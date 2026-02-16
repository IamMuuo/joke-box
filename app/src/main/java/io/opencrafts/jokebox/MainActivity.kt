package io.opencrafts.jokebox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.opencrafts.jokebox.screens.JokeBoxScreen
import io.opencrafts.jokebox.ui.theme.JokeBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JokeBoxTheme {
                JokeBoxScreen()
            }
        }
    }
}