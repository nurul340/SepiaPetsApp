package com.sepiainnovations.petsapp.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sepiainnovations.petsapp.R
import com.sepiainnovations.petsapp.databinding.ItemPetsBinding
import com.sepiainnovations.petsapp.model.data.PetDetails
import com.squareup.picasso.Picasso

class PetListAdapter(
    val itemClickListener: (item: PetDetails)-> Unit
) : RecyclerView.Adapter<PetListAdapter.PetViewHolder>() {

    private val dataList = mutableListOf<PetDetails>()

    inner class PetViewHolder(private val binding: ItemPetsBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(petItem: PetDetails) {
            binding.tvPetName.text = petItem.title

            Picasso.get().load(petItem.image_url)
                .placeholder(R.drawable.placeholdet_pet_pow)
                .into(binding.ivPetImage)

            binding.root.setOnClickListener {
                itemClickListener.invoke(petItem)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(foodList: List<PetDetails>) {
        this.dataList.clear()
        this.dataList.addAll(foodList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val binding = ItemPetsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}