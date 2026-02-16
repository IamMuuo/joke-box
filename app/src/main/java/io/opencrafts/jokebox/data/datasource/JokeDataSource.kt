package io.opencrafts.jokebox.data.datasource

import io.opencrafts.jokebox.data.dto.JokeDto

interface JokeDataSource {
    suspend fun getJoke(category: String): JokeDto
}