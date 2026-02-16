package io.opencrafts.jokebox.presentation.viewmodels

import io.opencrafts.jokebox.domain.model.Joke

sealed  interface  JokeUiState {
    object Loading : JokeUiState
    data class Success(val joke: Joke) : JokeUiState
    data class Error(val message: String) : JokeUiState
    object Empty : JokeUiState
}