package io.opencrafts.jokebox.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.opencrafts.jokebox.domain.usecases.GetJokeUseCase
import kotlinx.coroutines.launch


// Notice now that the view model is smaller and easily maintainable
// and readable too???
// Notice there is no retrofit stuff?
class JokeViewModel(
    private val getJokeUseCase: GetJokeUseCase
) : ViewModel() {

    // Internal state is private, exposed as read-only to the UI
    var uiState by mutableStateOf<JokeUiState>(JokeUiState.Empty)
        private set

    fun fetchJoke(category: String = "Programming") {
        viewModelScope.launch {
            uiState = JokeUiState.Loading

            // Execute the Use Case (The 'invoke' operator we created)
            val result = getJokeUseCase(category)

            uiState = result.fold(
                onSuccess = { JokeUiState.Success(it) },
                onFailure = { JokeUiState.Error(it.message ?: "Unknown Error") }
            )
        }
    }
}