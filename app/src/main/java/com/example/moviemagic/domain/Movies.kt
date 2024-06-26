package com.example.moviemagic.domain

import com.google.gson.annotations.SerializedName

data class SearchResult(
	@SerializedName("Search") val search: List<Movie>,
	@SerializedName("totalResults") val totalResults: String,
	@SerializedName("Response") val response: String
)

data class Movie(
	@SerializedName("Title") val title: String,
	@SerializedName("Year") val year: String,
	@SerializedName("imdbID") val imdbID: String,
	@SerializedName("Type") val type: String,
	@SerializedName("Poster") val poster: String
)