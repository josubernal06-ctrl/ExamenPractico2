package com.example.examenpractico2.data.api.service

import com.example.examenpractico2.data.api.dto.catfact.CatFactsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactApiService {
    @GET("facts")
    suspend fun getFacts(@Query("limit") limit: Int = 10): CatFactsResponseDto
}
