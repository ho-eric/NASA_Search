package com.example.nasasearch.ui.theme.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.nasasearch.NASASearchApplication
import com.example.nasasearch.data.NASADataRepository
import com.example.nasasearch.model.Collection
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface NASAUiState {
    data class Success(val photos: Collection) : NASAUiState
    object Error : NASAUiState
    object Loading : NASAUiState
}

class NASAViewModel(private val nasaDataRepository: NASADataRepository) : ViewModel() {
    var nasaUiState: NASAUiState by mutableStateOf(NASAUiState.Loading)
        private set

    var nasaImage: String by mutableStateOf("")
        private set
    var nasaTitle: String by mutableStateOf("")
        private set

    var nasaDescription: String by mutableStateOf("")
        private set

    var nasaCreationDate: String by mutableStateOf("")

    init {
        getNASAData("")
    }

    fun getNASAData(searchTerm: String) {
        viewModelScope.launch {
            nasaUiState = NASAUiState.Loading
            nasaUiState = try {
                NASAUiState.Success(nasaDataRepository.getNASAData(searchTerm))
            } catch (e: IOException) {
                NASAUiState.Error
            } catch (e: HttpException) {
                NASAUiState.Error
            }
        }
    }

    fun setDetailsScreen(image: String, title: String, description: String, creationDate: String) {
        viewModelScope.launch {
            nasaImage = image
            nasaTitle = title
            nasaDescription = description
            nasaCreationDate = creationDate
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NASASearchApplication)
                val nasaSearchRepository = application.container.nasaDataRepository
                NASAViewModel(nasaDataRepository = nasaSearchRepository)
            }
        }
    }
}