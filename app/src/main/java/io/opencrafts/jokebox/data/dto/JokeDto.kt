package io.opencrafts.jokebox.data.dto

import io.opencrafts.jokebox.domain.model.Joke

data class JokeDto(
    val setup: String?,
    val delivery: String?,
    val type: String
)

fun JokeDto.toDomain() = Joke(
    setup = this.setup ?: "",
    punchLine = this.delivery ?: "",
)
