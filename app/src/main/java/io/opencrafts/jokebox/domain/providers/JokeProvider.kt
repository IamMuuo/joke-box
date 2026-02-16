package io.opencrafts.jokebox.domain.providers

import io.opencrafts.jokebox.domain.model.Joke

interface JokeProvider {
    suspend fun getJoke(): Joke
}