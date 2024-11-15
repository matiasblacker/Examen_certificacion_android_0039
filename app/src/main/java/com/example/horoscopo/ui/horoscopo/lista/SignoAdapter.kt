package com.example.horoscopo.ui.horoscopo.lista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.horoscopo.data.model.SignoModel
import com.example.horoscopo.databinding.ItemBinding

class SignoAdapter(private val onItemClick: (Int) -> Unit) : ListAdapter<SignoModel, SignoAdapter.SignoViewHolder>(
    SignoDiffCallback()
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignoViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return SignoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SignoViewHolder, position: Int) {
        val signoModel = getItem(position)
        holder.bind(signoModel)
    }

    inner class SignoViewHolder(private val binding: ItemBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bind(signoModel: SignoModel) {
            binding.nombreSigno.text = signoModel.nombre
            Glide.with(binding.root.context)
                .load(signoModel.logo)
                .into(binding.ImagenSigno)

            binding.root.setOnClickListener {
                onItemClick(signoModel.id)
            }
        }
    }

    class SignoDiffCallback : DiffUtil.ItemCallback<SignoModel>() {
        override fun areItemsTheSame(oldItem: SignoModel, newItem: SignoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SignoModel, newItem: SignoModel): Boolean {
            return oldItem == newItem
        }
    }
}


