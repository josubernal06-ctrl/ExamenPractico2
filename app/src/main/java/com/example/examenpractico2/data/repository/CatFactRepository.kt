package com.example.examenpractico2.data.repository

import com.example.examenpractico2.data.api.client.RetrofitClients
import com.example.examenpractico2.data.mapper.Mapeadores
import com.example.examenpractico2.domain.model.CatFact

class CatFactRepository {
    private val api = RetrofitClients.catFactApiService

    suspend fun getFacts(): Result<List<CatFact>> {
        return try {
            val response = api.getFacts()
            val domainModels = response.data.map { Mapeadores.catFactToDomain(it) }
            Result.success(domainModels)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
