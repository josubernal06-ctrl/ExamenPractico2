package com.example.examenpractico2.data.api.service

import com.example.examenpractico2.data.api.dto.weather.OpenMeteoResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteoApiService {
    @GET("forecast")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double = -16.5,
        @Query("longitude") longitude: Double = -68.15,
        @Query("current") current: String = "temperature_2m,relative_humidity_2m,wind_speed_10m",
        @Query("timezone") timezone: String = "America/La_Paz"
    ): OpenMeteoResponseDto
}
