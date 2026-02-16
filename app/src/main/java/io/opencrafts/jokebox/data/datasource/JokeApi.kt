package io.opencrafts.jokebox.data.datasource

import io.opencrafts.jokebox.data.dto.JokeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokeApi {
    @GET("joke/{category}")
    suspend fun fetchJoke(
        @Path("category") category: String,
        @Query("type") type: String = "twopart",
        @Query("blacklistFlags") flags: String = "nsfw,religious,political,racist,sexist,explicit"
    ): JokeDto

}