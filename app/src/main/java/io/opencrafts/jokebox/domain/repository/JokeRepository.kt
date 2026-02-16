package io.opencrafts.jokebox.domain.repository

import io.opencrafts.jokebox.domain.model.Joke

interface JokeRepository {
    suspend fun getJoke(category: String): Joke
}