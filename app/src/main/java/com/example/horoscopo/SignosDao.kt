package com.example.horoscopo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SignosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(horoscopes: List<SignoModel>)

    @Query("SELECT * FROM SignoModel")
    suspend fun getAllSignos(): List<SignoModel>


    @Query("SELECT * FROM SignoDetalle WHERE id = :id")
    suspend fun getDetalleSigno(id: Int): SignoDetalle?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetalleSigno(horoscopeDetail: SignoDetalle)
}