package com.example.nasasearch

import android.app.Application
import com.example.nasasearch.data.AppContainer
import com.example.nasasearch.data.DefaultAppContainer

class NASASearchApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}