package com.example.horoscopo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.horoscopo.databinding.FragmentListaHoroscopoBinding

class ListaHoroscopo : Fragment() {

    private lateinit var binding: FragmentListaHoroscopoBinding
    private lateinit var viewModel: SignoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListaHoroscopoBinding.inflate(inflater, container, false)

        val apiService = ApiService.create()
        val database = SignosDatabase.getInstance(requireContext())
        val repository = SignoRepository(apiService, database)

        viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T{
                    return SignoViewModel(repository) as T
                }
            }
        ).get(SignoViewModel::class.java)

        binding.RecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = SignoAdapter{ signoId ->
            val action = ListaHoroscopoDirections.actionListaHoroscopoToDetalleHoroscopo(signoId)
            findNavController().navigate(action)
        }
        binding.RecyclerView.adapter = adapter

        viewModel.signoModel.observe(viewLifecycleOwner,{ signo ->
            adapter.submitList(signo)  // Update RecyclerView with the new data from the ViewModel.  // This is a one-way data binding, so changes in the ViewModel will automatically be reflected in the RecyclerView.  // This is a good place to fetch new data from the API or database.  // In a real-world application, you'd also want to handle any errors that might occur during the fetch operation.  // For example, you could display a loading indicator or handle a case where the API response is empty.  // Note that you need to update the RecyclerView with the new data inside the Observer block, not inside the fetch operation.  // This is because the Observer block is called after the fetch operation completes, and it's a good place to update the RecyclerView with the new data.  // If you update the RecyclerView inside the fetch operation, it won't be visible to the user until the fetch operation completes.  // If you want to update the'
        })

        return binding.root
    }
}