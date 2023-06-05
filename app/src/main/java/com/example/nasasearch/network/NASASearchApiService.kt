package com.example.nasasearch.network

import com.example.nasasearch.model.Collection
import retrofit2.http.GET
import retrofit2.http.Query

interface NASASearchApiService {
    @GET("search")
    suspend fun getData(
        @Query("q") searchTerm: String,
        @Query("media_type") mediaType: String = "image"
    ): Collection

}