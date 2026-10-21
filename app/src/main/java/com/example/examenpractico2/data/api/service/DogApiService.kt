package com.example.examenpractico2.data.api.service

import com.example.examenpractico2.data.api.dto.dog.DogBreedsResponseDto
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/list/all")
    suspend fun getBreeds(): DogBreedsResponseDto
}
