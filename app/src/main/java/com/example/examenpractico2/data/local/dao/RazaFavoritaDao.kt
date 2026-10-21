package com.example.examenpractico2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.examenpractico2.data.local.entity.RazaFavoritaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RazaFavoritaDao {
    @Query("SELECT * FROM razas_favoritas")
    fun observarFavoritos(): Flow<List<RazaFavoritaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(raza: RazaFavoritaEntity)

    @Query("DELETE FROM razas_favoritas WHERE nombre = :nombre")
    suspend fun eliminar(nombre: String)
}
