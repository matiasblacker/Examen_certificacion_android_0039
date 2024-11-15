package com.example.horoscopo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignoRepository(private val apiService: ApiService, private val database: SignosDatabase) {

    suspend fun getSignos() = withContext(Dispatchers.IO) {
        val response = apiService.getSignos()
        if (response.isSuccessful) {
            response.body()?.let { signos ->

                println("Horoscopes response: $signos")


                val processedHoroscopes = signos.map { apiResponse ->
                    SignoModel(
                        id = apiResponse.id,
                        nombre = apiResponse.nombre ?: "Desconocido",
                        fechas = apiResponse.fechas ?: "Fecha no disponible",
                        elemento = apiResponse.elemento ?: "",
                        planetaRegente = apiResponse.planetaRegente ?: "",
                        simbolo = apiResponse.simbolo ?: "",
                        color = apiResponse.color ?: "#FFFFFF",
                        logo = apiResponse.logo ?: ""
                    )
                }


                database.signosDao().insertAll(processedHoroscopes)
            }
        } else {
            println("API Error: ${response.code()}")
        }
        database.signosDao().getAllSignos()
    }

    suspend fun getDetalleSigno(id: Int): SignoDetalle? = withContext(Dispatchers.IO) {
        val response = apiService.getDetalleSigno(id)
        if (response.isSuccessful) {
            response.body()?.let { detalleSignos ->

                database.signosDao().insertDetalleSigno(detalleSignos)
               return@withContext detalleSignos
            }
        }
        return@withContext database.signosDao().getDetalleSigno(id)
    }
}