package com.example.horoscopo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SignoModel(
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

    @SerializedName("logo")
    val logo: String? = null
)