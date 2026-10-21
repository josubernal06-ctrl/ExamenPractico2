package com.example.examenpractico2.data.repository

import com.example.examenpractico2.data.api.client.RetrofitClients
import com.example.examenpractico2.data.mapper.Mapeadores
import com.example.examenpractico2.domain.model.Weather

class WeatherRepository {
    private val api = RetrofitClients.openMeteoApiService

    suspend fun getWeather(): Result<Weather> {
        return try {
            val response = api.getCurrentWeather()
            Result.success(Mapeadores.weatherToDomain(response))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
