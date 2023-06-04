package com.example.nasasearch.data

import com.example.nasasearch.model.Collection
import com.example.nasasearch.network.NASASearchApiService

interface NASADataRepository {
    suspend fun getNASAData(): Collection
}


class NetworkNASADataRepository(
    private val nasaApiService: NASASearchApiService
) : NASADataRepository {
    override suspend fun getNASAData(): Collection = nasaApiService.getData()
}