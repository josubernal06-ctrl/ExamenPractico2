package com.example.examenpractico2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenpractico2.data.repository.CatFactRepository
import com.example.examenpractico2.domain.model.CatFact
import com.example.examenpractico2.presentation.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatFactViewModel : ViewModel() {
    private val repository = CatFactRepository()

    private val _uiState = MutableStateFlow<UiState<List<CatFact>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<CatFact>>> = _uiState.asStateFlow()

    init {
        cargarDatos()
    }

    fun cargarDatos() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = repository.getFacts()
            result.onSuccess { facts ->
                _uiState.value = UiState.Success(facts)
            }.onFailure { error ->
                _uiState.value = UiState.Error(error.localizedMessage ?: "Error al cargar datos de gatos")
            }
        }
    }

    fun reintentar() {
        cargarDatos()
    }
}
