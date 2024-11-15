package com.example.horoscopo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers


class SignoViewModel(private val repository: SignoRepository) : ViewModel() {
    val signoModel = liveData(Dispatchers.IO) {
        // Emitimos la lista de signos obtenida desde el repositorio
        val signos = repository.getSignos()
        emit(signos) // 'emit' se usa para enviar los datos a LiveData
    }
}