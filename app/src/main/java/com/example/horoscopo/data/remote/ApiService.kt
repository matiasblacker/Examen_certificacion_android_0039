package com.example.horoscopo.data.remote

import com.example.horoscopo.data.model.SignoDetalle
import com.example.horoscopo.data.model.SignoModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("sign")
    suspend fun getSignos(): Response<List<SignoModel>>

    @GET("sign/{id}")
    suspend fun getDetalleSigno(@Path("id") id: Int): Response<SignoDetalle>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://zodiac-api-two.vercel.app/es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}