package com.example.examenpractico2.data.api.client

import com.example.examenpractico2.data.api.service.CatFactApiService
import com.example.examenpractico2.data.api.service.DogApiService
import com.example.examenpractico2.data.api.service.OpenMeteoApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClients {
    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val dogApiService: DogApiService by lazy {
        createRetrofit("https://dog.ceo/api/").create(DogApiService::class.java)
    }

    val openMeteoApiService: OpenMeteoApiService by lazy {
        createRetrofit("https://api.open-meteo.com/v1/").create(OpenMeteoApiService::class.java)
    }

    val catFactApiService: CatFactApiService by lazy {
        createRetrofit("https://catfact.ninja/").create(CatFactApiService::class.java)
    }
}
