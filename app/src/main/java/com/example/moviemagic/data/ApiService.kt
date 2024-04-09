package com.example.moviemagic.data


import com.example.moviemagic.domain.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
	
	
	@GET("/")
	suspend fun getMovies(
		@Query("s") query: String = "new", @Query("type") type: String = "movie",
		@Query("i") i: String = "tt3896198",
		@Query("apiKey") apiKey: String = "ec8a996",
	): SearchResult
}

