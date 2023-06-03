package com.example.nasasearch.ui.theme.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nasasearch.model.Collection
import com.example.nasasearch.model.Item
import com.example.nasasearch.network.NASAApi
import com.example.nasasearch.network.NASASearchApiService
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface NASAUiState {
    data class Success(val photos: Item) : NASAUiState
    object Error : NASAUiState
    object Loading : NASAUiState
}

class NASAViewModel : ViewModel() {
    var nasaUiState: NASAUiState by mutableStateOf(NASAUiState.Loading)
        private set

    init {
        getNASAData()
    }

    fun getNASAData() {
        viewModelScope.launch {
            nasaUiState = try{
                val result = NASAApi.retrofitService.getData().collection.items[0].links[0].href
                NASAUiState.Success(NASAApi.retrofitService.getData().collection.items[0])
            } catch (e: IOException) {
                NASAUiState.Error
            }
        }
    }
}