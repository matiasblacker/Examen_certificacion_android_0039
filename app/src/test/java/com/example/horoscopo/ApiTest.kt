package com.example.horoscopo

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {
    private val apiService: ApiService = Retrofit.Builder()
        .baseUrl("https://zodiac-api-two.vercel.app/es/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Test
    fun testDetalleSigno() = runBlocking {
        val response = apiService.getDetalleSigno(3)
        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
    }
    @Test
    fun testSignos() = runBlocking {
        try {
            val response = apiService.getSignos()

            assertTrue(response.isSuccessful)
            assertEquals(200, response.code())

            val responseBody = response.body()
            assertNotNull(responseBody)

            assertTrue(responseBody is List<*>)
        } catch (e: Exception) {

            fail("Test falló debido a una excepción: ${e.message}")
        }
    }

}