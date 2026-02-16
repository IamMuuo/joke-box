package io.opencrafts.jokebox.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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

    // List of categories supported by JokeAPI
    val categories = listOf("Programming", "Misc", "Dark", "Pun", "Spooky", "Christmas")
    // Keep track of which one is selected
    var selectedCategory by remember { mutableStateOf(categories[0]) }

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
            FloatingActionButton(onClick = {viewModel.fetchJoke(selectedCategory)}) {
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

            LazyRow(
                modifier = Modifier.fillMaxWidth().padding(8.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category },
                        label = { Text(category) }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center // Centers the children (Loader/Card/Error)
            ) {

                when (state) {
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
}

// Since the view model is 'depended' upon here how do we actually test???
//@Preview(showBackground = true)
//@Composable
//fun JokeBoxScreenPreview() = JokeBoxScreen()