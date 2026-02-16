package io.opencrafts.jokebox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.opencrafts.jokebox.presentation.screens.AboutDeveloperScreen
import io.opencrafts.jokebox.presentation.screens.JokeBoxScreen
import io.opencrafts.jokebox.ui.theme.JokeBoxTheme
import io.opencrafts.jokebox.presentation.viewmodels.JokeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JokeBoxTheme {

                val viewModel = JokeViewModel()
                var showAboutPage by remember { mutableStateOf(false) }
                
                Crossfade(targetState = showAboutPage, label = "ScreenTransition") { isAboutPage ->
                    if (isAboutPage) {
                        AboutDeveloperScreen(onBack = { showAboutPage = false })
                    } else {
                        JokeBoxScreen(
                            viewModel = viewModel,
                            onInfoClick = { showAboutPage = true }
                        )
                    }
                }
            }
        }
    }
}