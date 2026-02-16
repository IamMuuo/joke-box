package io.opencrafts.jokebox.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import io.opencrafts.jokebox.presentation.components.FlippableJokeCard
import io.opencrafts.jokebox.presentation.viewmodels.JokeUiState
import io.opencrafts.jokebox.presentation.viewmodels.JokeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeBoxScreen(
    viewModel: JokeViewModel,
    onInfoClick: () -> Unit
) {
    val state = viewModel.uiState
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        "Joke Box",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold
                    )

                },
                actions = {
                    IconButton(onClick = onInfoClick) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "About Developer",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {viewModel.fetchJoke()}) {
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Add")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            when(state){
                is JokeUiState.Loading -> CircularProgressIndicator()
                is JokeUiState.Success -> {
                    FlippableJokeCard(state.joke.setup, state.joke.punchLine)
                }
                is JokeUiState.Error -> Text("Error: ${state.message}", color = Color.Red)
                is JokeUiState.Empty -> Text("Press the button to get started!")

            }
        }
    }
}

// Since the view model is 'depended' upon here how do we actually test???
//@Preview(showBackground = true)
//@Composable
//fun JokeBoxScreenPreview() = JokeBoxScreen()