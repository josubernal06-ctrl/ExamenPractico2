package com.example.examenpractico2.data.api.dto.weather

import com.google.gson.annotations.SerializedName

data class OpenMeteoResponseDto(
    @SerializedName("current") val current: CurrentWeatherDto
)

data class CurrentWeatherDto(
    @SerializedName("time") val time: String,
    @SerializedName("interval") val interval: Int,
    @SerializedName("temperature_2m") val temperature_2m: Double,
    @SerializedName("relative_humidity_2m") val relative_humidity_2m: Int,
    @SerializedName("wind_speed_10m") val wind_speed_10m: Double
)
