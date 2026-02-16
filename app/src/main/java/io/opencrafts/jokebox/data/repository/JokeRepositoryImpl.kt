package io.opencrafts.jokebox.data.repository

import io.opencrafts.jokebox.data.datasource.JokeDataSource
import io.opencrafts.jokebox.data.dto.toDomain
import io.opencrafts.jokebox.domain.model.Joke
import io.opencrafts.jokebox.domain.repository.JokeRepository

class JokeRepositoryImpl(private val jokeDataSource: JokeDataSource) : JokeRepository{
    override suspend fun getJoke(category: String): Joke {
        return jokeDataSource.getJoke(category).toDomain()
    }
}