package com.example.moviemagic.di

import com.example.moviemagic.data.MovieRepository
import com.example.moviemagic.data.MovieRepositoryImp
import com.example.moviemagic.data.RetrofitInstance
import com.example.moviemagic.domain.GetMoviesUseCase
import com.example.moviemagic.domain.SearchMovieUseCase
import com.example.moviemagic.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
	
	single { RetrofitInstance.createApiService() }
	single<MovieRepository> { MovieRepositoryImp(get()) }
	single { GetMoviesUseCase(get()) }
	single { SearchMovieUseCase(get()) }
	viewModel { MoviesViewModel(get(), get()) }
}