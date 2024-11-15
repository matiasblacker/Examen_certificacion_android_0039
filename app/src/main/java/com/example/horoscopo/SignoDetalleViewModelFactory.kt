package com.example.horoscopo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SignoDetalleViewModelFactory(private val repository: SignoRepository) : ViewModelProvider.Factory{
    override fun<T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(SignoDetalleViewModel::class.java)) {
            return SignoDetalleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}