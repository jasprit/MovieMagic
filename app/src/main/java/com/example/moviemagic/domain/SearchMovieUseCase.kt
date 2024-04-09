package com.example.moviemagic.domain

import com.example.moviemagic.data.MovieRepository
import retrofit2.http.Query

class SearchMovieUseCase(private val movieRepository: MovieRepository) {
	
	suspend operator fun invoke(query: String): SearchResult {
		return movieRepository.searchMovies(query)
	}
}