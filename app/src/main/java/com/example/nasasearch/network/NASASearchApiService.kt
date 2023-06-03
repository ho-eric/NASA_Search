package com.example.nasasearch.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://images-api.nasa.gov/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NASASearchApiService {
    @GET("search?q=mars&media_type=image")
    suspend fun getData(): String
}

object NASAApi {
    val retrofitService : NASASearchApiService by lazy {
        retrofit.create(NASASearchApiService::class.java)
    }
}