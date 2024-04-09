package com.example.moviemagic.domain

import com.example.moviemagic.data.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
	
	suspend operator fun invoke(): SearchResult {
		return movieRepository.getMovies()
	}
}