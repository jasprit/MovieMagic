package com.example.moviemagic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemagic.domain.GetMoviesUseCase
import com.example.moviemagic.domain.SearchMovieUseCase
import com.example.moviemagic.domain.SearchResult
import kotlinx.coroutines.launch


class MoviesViewModel(
	private val getMoviesUseCase: GetMoviesUseCase,
	private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {
	
	private val _movies = MutableLiveData<SearchResult>()
	val movies: LiveData<SearchResult> = _movies
	
	private val _searchMovies = MutableLiveData<SearchResult>()
	val searchMovies: LiveData<SearchResult> = _searchMovies
	
	fun getMovies() {
		viewModelScope.launch {
			val result = getMoviesUseCase()
			_movies.value = result
		}
	}
	
	fun searchMovies(query: String) {
		if (query.length > 1) {
			viewModelScope.launch {
				val result = searchMovieUseCase(query)
				_searchMovies.value = result
				_movies.value = result
			}
		}
	}
}