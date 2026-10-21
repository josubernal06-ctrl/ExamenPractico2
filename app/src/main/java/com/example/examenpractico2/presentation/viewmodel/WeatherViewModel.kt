package com.example.examenpractico2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenpractico2.data.repository.WeatherRepository
import com.example.examenpractico2.domain.model.Weather
import com.example.examenpractico2.presentation.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()

    private val _uiState = MutableStateFlow<UiState<Weather>>(UiState.Loading)
    val uiState: StateFlow<UiState<Weather>> = _uiState.asStateFlow()

    init {
        cargarClima()
    }

    fun cargarClima() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = repository.getWeather()
            result.onSuccess { weather ->
                _uiState.value = UiState.Success(weather)
            }.onFailure { error ->
                _uiState.value = UiState.Error(error.localizedMessage ?: "Error al cargar el clima")
            }
        }
    }

    fun reintentar() {
        cargarClima()
    }
}
