package com.example.moviemagic.data


import com.example.moviemagic.domain.SearchResult

interface MovieRepository {
	suspend fun getMovies(): SearchResult
	suspend fun searchMovies(query: String): SearchResult
}

class MovieRepositoryImp(val apiService: ApiService) : MovieRepository {
	override suspend fun getMovies(): SearchResult {
		return apiService.getMovies()
	}
	
	override suspend fun searchMovies(query: String): SearchResult {
		return apiService.getMovies(query)
	}
}