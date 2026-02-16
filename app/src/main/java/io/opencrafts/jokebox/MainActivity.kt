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
import io.opencrafts.jokebox.data.datasource.JokeApi
import io.opencrafts.jokebox.data.datasource.JokeRemoteDataSource
import io.opencrafts.jokebox.data.repository.JokeRepositoryImpl
import io.opencrafts.jokebox.domain.usecases.GetJokeUseCase
import io.opencrafts.jokebox.presentation.screens.AboutDeveloperScreen
import io.opencrafts.jokebox.presentation.screens.JokeBoxScreen
import io.opencrafts.jokebox.ui.theme.JokeBoxTheme
import io.opencrafts.jokebox.presentation.viewmodels.JokeViewModel
import org.koin.androidx.compose.koinViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JokeBoxTheme {
                var showAboutPage by remember { mutableStateOf(false) }
                val jokeViewModel: JokeViewModel = koinViewModel()
                Crossfade(targetState = showAboutPage, label = "ScreenTransition") { isAboutPage ->
                    if (isAboutPage) {
                        AboutDeveloperScreen(onBack = { showAboutPage = false })
                    } else {
                        JokeBoxScreen(
                            viewModel = jokeViewModel,
                            onInfoClick = { showAboutPage = true }
                        )
                    }
                }
            }
        }
    }
}