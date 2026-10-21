package com.example.examenpractico2.data.mapper

import com.example.examenpractico2.data.api.dto.catfact.CatFactDto
import com.example.examenpractico2.data.api.dto.dog.DogBreedsResponseDto
import com.example.examenpractico2.data.api.dto.weather.OpenMeteoResponseDto
import com.example.examenpractico2.data.local.entity.RazaFavoritaEntity
import com.example.examenpractico2.domain.model.CatFact
import com.example.examenpractico2.domain.model.DogBreed
import com.example.examenpractico2.domain.model.Weather

object Mapeadores {
    fun dogBreedsToDomain(dto: DogBreedsResponseDto): List<DogBreed> {
        return dto.message.map { (name, subbreeds) ->
            DogBreed(
                name = name,
                subbreeds = subbreeds,
                subbreedCount = subbreeds.size
            )
        }
    }

    fun weatherToDomain(dto: OpenMeteoResponseDto): Weather {
        val current = dto.current
        return Weather(
            temperature = current.temperature_2m,
            humidity = current.relative_humidity_2m,
            windSpeed = current.wind_speed_10m,
            time = current.time
        )
    }

    fun catFactToDomain(dto: CatFactDto): CatFact {
        return CatFact(
            fact = dto.fact,
            length = dto.length
        )
    }

    fun entityToDomain(entity: RazaFavoritaEntity): DogBreed {
        return DogBreed(
            name = entity.nombre,
            subbreeds = emptyList(),
            subbreedCount = entity.cantidadSubrazas
        )
    }

    fun domainToEntity(raza: DogBreed): RazaFavoritaEntity {
        return RazaFavoritaEntity(
            nombre = raza.name,
            cantidadSubrazas = raza.subbreedCount
        )
    }
}
