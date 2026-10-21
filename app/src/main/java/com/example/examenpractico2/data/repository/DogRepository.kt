package com.example.examenpractico2.data.repository

import com.example.examenpractico2.data.api.client.RetrofitClients
import com.example.examenpractico2.data.mapper.Mapeadores
import com.example.examenpractico2.domain.model.DogBreed

class DogRepository {
    private val api = RetrofitClients.dogApiService

    suspend fun getBreeds(): Result<List<DogBreed>> {
        return try {
            val response = api.getBreeds()
            Result.success(Mapeadores.dogBreedsToDomain(response))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
