package io.opencrafts.jokebox.domain.usecases

import io.opencrafts.jokebox.core.domain.BaseUseCase
import io.opencrafts.jokebox.domain.model.Joke
import io.opencrafts.jokebox.domain.repository.JokeRepository

class GetJokeUseCase(private val jokeRepository: JokeRepository):
    BaseUseCase<String, Result<Joke>> {
    override suspend fun invoke(param: String): Result<Joke> {
        return try {
            val joke = jokeRepository.getJoke(param)
            Result.success(joke)
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }
}