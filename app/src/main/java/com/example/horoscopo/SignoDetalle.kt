package com.example.horoscopo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity
data class SignoDetalle(
    @PrimaryKey val id: Int,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("fechas")
    val fechas: String,

    @SerializedName("elemento")
    val elemento: String? = null,

    @SerializedName("planetaRegente")
    val planetaRegente: String? = null,

    @SerializedName("simbolo")
    val simbolo: String? = null,

    @SerializedName("color")
    val color: String? = null,

    @SerializedName("imagen")
    val imageUrl: String? = null,

    @SerializedName("fortalezas")
    val fortalezas: List<String>? = null,

    @SerializedName("debilidades")
    val debilidades: List<String>? = null,

    @SerializedName("descripcion")
    val descripcion: String? = null,

    @SerializedName("compatibilidad")
    val compatibilidad: List<String>? = null
)
