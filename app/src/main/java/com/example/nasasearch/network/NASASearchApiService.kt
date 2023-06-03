package com.example.nasasearch.network

import com.example.nasasearch.model.Collection
import retrofit2.Retrofit
import retrofit2.http.GET
import kotlinx.serialization.json.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType


private const val BASE_URL = "https://images-api.nasa.gov/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json{ ignoreUnknownKeys = true; isLenient = true; }.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface NASASearchApiService {
    @GET("search?q=mars&media_type=image")
    suspend fun getData(): Collection
}

object NASAApi {
    val retrofitService : NASASearchApiService by lazy {
        retrofit.create(NASASearchApiService::class.java)
    }
}