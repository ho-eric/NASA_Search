package com.example.nasasearch.data

import com.example.nasasearch.network.NASASearchApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val nasaDataRepository: NASADataRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://images-api.nasa.gov/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json{ ignoreUnknownKeys = true; isLenient = true; }
            .asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: NASASearchApiService by lazy {
        retrofit.create(NASASearchApiService::class.java)
    }

    override val nasaDataRepository: NASADataRepository by lazy {
        NetworkNASADataRepository(retrofitService)
    }
}

