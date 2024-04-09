package com.example.moviemagic.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
	
	private const val BASE_URL = "https://www.omdbapi.com/"
	
	private val retrofit: Retrofit by lazy {
		Retrofit.Builder().baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
	
	fun createApiService(): ApiService {
		return retrofit.create(ApiService::class.java)
	}
}