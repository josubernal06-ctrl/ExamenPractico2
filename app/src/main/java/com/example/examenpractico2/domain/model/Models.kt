package com.example.examenpractico2.domain.model

data class DogBreed(
    val name: String,
    val subbreeds: List<String>,
    val subbreedCount: Int
)

data class Weather(
    val temperature: Double,
    val humidity: Int,
    val windSpeed: Double,
    val time: String
)

data class CatFact(
    val fact: String,
    val length: Int
)
