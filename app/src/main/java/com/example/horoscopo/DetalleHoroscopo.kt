package com.example.horoscopo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.horoscopo.databinding.FragmentDetalleHoroscopoBinding
import com.example.horoscopo.databinding.FragmentListaHoroscopoBinding


class DetalleHoroscopo : Fragment() {

    private lateinit var binding: FragmentDetalleHoroscopoBinding
    private lateinit var viewModel: SignoDetalleViewModel
    private var signoId: Int = 0
    private lateinit var repository: SignoRepository
    private lateinit var apiService: ApiService
    private lateinit var database: SignosDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetalleHoroscopoBinding.inflate(inflater, container, false)
        database = SignosDatabase.getInstance(requireContext())
        apiService = ApiService.create()
        repository = SignoRepository(apiService, database)

        val factory = SignoDetalleViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(SignoDetalleViewModel::class.java)

        signoId = arguments?.getInt("signoId", 0)?:0
        // Cargar el detalle del signo solo si signoId es válido
        if (signoId != 0) {
            viewModel.cargarDetalleSigno(signoId)
        }

        viewModel.signoDetalle.observe(viewLifecycleOwner, {
                detalle ->

            if(detalle != null){

                // Carga la imagen
                Glide.with(this)
                    .load(detalle.imageUrl)
                    .into(binding.imagenSigno)
                binding.nombreSigno.text = detalle.nombre
                binding.descripcionSigno.text = detalle.fechas
                binding.descripcionSigno.append("\n\nDescripción: ${detalle.descripcion}")
                binding.descripcionSigno.append("\nElemento: ${detalle.elemento}")
                binding.descripcionSigno.append("\nPlaneta Regente: ${detalle.planetaRegente}")
                binding.descripcionSigno.append("\nSímbolo: ${detalle.simbolo}")
                binding.descripcionSigno.append("\nColor: ${detalle.color}")

                binding.imageButton.setOnClickListener {
                    requireActivity().supportFragmentManager.popBackStack()
                }

                binding.enviarEmail.setOnClickListener {
                    sendEmail(detalle.nombre ?: "")
                }
            }
        })

        return binding.root
    }

    private fun sendEmail(signo: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("tuhoroscopodiario@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Información sobre mi Horóscopo - $signo")
            putExtra(Intent.EXTRA_TEXT, "Solicito más información sobre mi signo $signo,\n" +
                    " además, quiero suscribirme al horóscopo diario y las últimas noticias.")
        }
        startActivity(intent)
    }
}