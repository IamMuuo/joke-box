package io.opencrafts.jokebox.di

import io.opencrafts.jokebox.data.datasource.JokeApi
import io.opencrafts.jokebox.data.datasource.JokeDataSource
import io.opencrafts.jokebox.data.datasource.JokeRemoteDataSource
import io.opencrafts.jokebox.data.repository.JokeRepositoryImpl
import io.opencrafts.jokebox.domain.repository.JokeRepository
import io.opencrafts.jokebox.domain.usecases.GetJokeUseCase
import io.opencrafts.jokebox.presentation.viewmodels.JokeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // 1. Retrofit Instance (Single)
    single <JokeApi>{
        Retrofit.Builder()
            .baseUrl("https://v2.jokeapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokeApi::class.java)
    }

    // 2. Data Sources (Bind the Interface to the Implementation)
    single<JokeDataSource> { JokeRemoteDataSource(get()) }

    // 3. Repository
    single<JokeRepository> { JokeRepositoryImpl(get()) }

    // 4. Use Case
    factory { GetJokeUseCase(get()) }

    // 5. ViewModel
    viewModel { JokeViewModel(get()) }
}