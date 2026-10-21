package com.example.examenpractico2.data.api.dto.catfact

import com.google.gson.annotations.SerializedName

data class CatFactsResponseDto(
    @SerializedName("data") val data: List<CatFactDto>
)

data class CatFactDto(
    @SerializedName("fact") val fact: String,
    @SerializedName("length") val length: Int
)
