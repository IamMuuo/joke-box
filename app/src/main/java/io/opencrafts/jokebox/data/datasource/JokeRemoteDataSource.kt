package io.opencrafts.jokebox.data.datasource

import io.opencrafts.jokebox.data.dto.JokeDto
import retrofit2.http.GET

class JokeRemoteDataSource(private val api : JokeApi): JokeDataSource {
    override suspend fun getJoke(category: String): JokeDto {
        return api.fetchJoke(category)
    }
}