package io.opencrafts.jokebox.core.domain

/**
 * P: The Parameter type (What the UseCase needs)
 * R: The Return type (What the UseCase gives back)
 */
interface BaseUseCase <in P, out R>{
    suspend operator fun  invoke(param: P): R
}
