package com.sepiainnovations.petsapp.views

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sepiainnovations.petsapp.R
import com.sepiainnovations.petsapp.databinding.FragmentPetDetailsBinding
import com.sepiainnovations.petsapp.utils.DataResult
import com.sepiainnovations.petsapp.viewmodel.PetsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PetDetailsFragment : BaseFragment(R.layout.fragment_pet_details) {

    private lateinit var petLisViewModel: PetsViewModel

    private lateinit var binding: FragmentPetDetailsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPetDetailsBinding.bind(view)

        petLisViewModel = ViewModelProvider(requireActivity())[PetsViewModel::class.java]

        petLisViewModel.selectedPetItem.observe(viewLifecycleOwner){
            binding.petDetails = it
        }

        petLisViewModel.contentDesc.observe(viewLifecycleOwner){
            when(it){
                is DataResult.ERROR -> {
                    binding.pbLoader.visibility = View.GONE
                    showErrorDialog(it.message)
                }
                is DataResult.LOADING -> {
                    binding.pbLoader.visibility = View.VISIBLE
                }
                is DataResult.SUCCESS -> {
                    binding.pbLoader.visibility = View.GONE
                    binding.wvContent.loadData(it.data!!, "text/html", "UTF-8")
                }
            }
        }
    }
}

