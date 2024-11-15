package com.example.horoscopo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignoDetalleViewModel(private val repository: SignoRepository) : ViewModel(){
    private val _signoDetalle = MutableLiveData<SignoDetalle?>()
    val signoDetalle: LiveData<SignoDetalle?> get() = _signoDetalle

    fun cargarDetalleSigno(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            val detalle = repository.getDetalleSigno(id)
            _signoDetalle.postValue(detalle)
        }
    }
}