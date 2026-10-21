package com.example.examenpractico2.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenpractico2.data.local.database.AppDatabase
import com.example.examenpractico2.data.mapper.Mapeadores
import com.example.examenpractico2.data.repository.FavoritosRepository
import com.example.examenpractico2.domain.model.DogBreed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritosViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).razaFavoritaDao()
    private val repository = FavoritosRepository(dao)

    private val _favoritos = MutableStateFlow<List<DogBreed>>(emptyList())
    val favoritos: StateFlow<List<DogBreed>> = _favoritos.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getFavoritos().collect { entities ->
                _favoritos.value = entities.map { Mapeadores.entityToDomain(it) }
            }
        }
    }

    fun marcarFavorita(raza: DogBreed) {
        viewModelScope.launch {
            repository.guardarFavorito(raza)
        }
    }

    fun quitarFavorita(nombre: String) {
        viewModelScope.launch {
            repository.eliminarFavorito(nombre)
        }
    }
}
