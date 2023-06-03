package com.example.nasasearch.ui.theme.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nasasearch.network.NASAApi
import kotlinx.coroutines.launch
import java.io.IOException

class NASAViewModel : ViewModel() {
    var nasaUiState: String by mutableStateOf("")
        private set

    init {
        getNASAData()
    }

    /**
     * Gets Mars photos information from the Mars API
     */
    fun getNASAData() {
        viewModelScope.launch {
            try{
                val listResult = NASAApi.retrofitService.getData()
                nasaUiState = listResult
            } catch (e: IOException) {

            }

        }
    }
}