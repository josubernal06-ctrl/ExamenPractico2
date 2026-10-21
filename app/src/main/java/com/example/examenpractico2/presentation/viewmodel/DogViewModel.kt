package com.example.examenpractico2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenpractico2.data.repository.DogRepository
import com.example.examenpractico2.domain.model.DogBreed
import com.example.examenpractico2.presentation.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {
    private val repository = DogRepository()

    private val _uiState = MutableStateFlow<UiState<List<DogBreed>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<DogBreed>>> = _uiState.asStateFlow()

    private var allBreeds: List<DogBreed> = emptyList()

    init {
        cargarRazas()
    }

    fun cargarRazas() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = repository.getBreeds()
            result.onSuccess { breeds ->
                allBreeds = breeds
                _uiState.value = UiState.Success(breeds)
            }.onFailure { error ->
                _uiState.value = UiState.Error(error.localizedMessage ?: "Error al cargar razas")
            }
        }
    }

    fun buscarRazas(texto: String) {
        if (_uiState.value is UiState.Success) {
            val filtered = if (texto.isBlank()) {
                allBreeds
            } else {
                allBreeds.filter { it.name.contains(texto, ignoreCase = true) }
            }
            _uiState.value = UiState.Success(filtered)
        }
    }

    fun reintentar() {
        cargarRazas()
    }
    
    fun getBreedByName(name: String): DogBreed? {
        return allBreeds.find { it.name == name }
    }
}
