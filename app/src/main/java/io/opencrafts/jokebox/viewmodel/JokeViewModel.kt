package io.opencrafts.jokebox.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


// This file directly violates SRP and other SOLID principles

data class JokeResponse(
    val setup: String? = null,
    val delivery: String? = null,
    val error: Boolean = false
)

interface JokeApi {
    // Note that there is tight coupling to the APi params even??
    @GET("joke/Programming,Dark?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=twopart")
    suspend fun fetchJoke(): JokeResponse
}

class JokeViewModel : ViewModel() {

    // 3. VIOLATION: The ViewModel creates its own low-level dependencies.
    // In a "DIP" world, these should be injected as abstractions.
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://v2.jokeapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(JokeApi::class.java)

    // 4. UI State is tied directly to the Network Model
    var jokeState by mutableStateOf<JokeResponse?>(null)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    init {
        fetchJoke()
    }

    fun fetchJoke() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = ""
            try {
                // 5. VIOLATION: The "Director" is doing the "Backstage" work.
                val response = api.fetchJoke()
                jokeState = response
            } catch (e: Exception) {
                errorMessage = "Failed to load joke: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}