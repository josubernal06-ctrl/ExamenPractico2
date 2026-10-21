package com.example.examenpractico2.data.repository

import com.example.examenpractico2.data.local.dao.RazaFavoritaDao
import com.example.examenpractico2.data.local.entity.RazaFavoritaEntity
import com.example.examenpractico2.domain.model.DogBreed
import com.example.examenpractico2.data.mapper.Mapeadores
import kotlinx.coroutines.flow.Flow

class FavoritosRepository(private val dao: RazaFavoritaDao) {
    fun getFavoritos(): Flow<List<RazaFavoritaEntity>> = dao.observarFavoritos()

    suspend fun guardarFavorito(raza: DogBreed) {
        dao.insertar(Mapeadores.domainToEntity(raza))
    }

    suspend fun eliminarFavorito(nombre: String) {
        dao.eliminar(nombre)
    }
}
