package com.example.moviemagic

import android.app.Application
import com.example.moviemagic.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {
	
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger() // Enable logger
			androidContext(this@MyApp)
			modules(appModule)
		}
	}
}