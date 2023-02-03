package com.sepiainnovations.petsapp.views

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sepiainnovations.petsapp.R
import com.sepiainnovations.petsapp.databinding.FragmentPetListBinding
import com.sepiainnovations.petsapp.model.data.PetDetails
import com.sepiainnovations.petsapp.utils.DataResult
import com.sepiainnovations.petsapp.viewmodel.PetsViewModel
import com.sepiainnovations.petsapp.views.adapters.PetListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PetListFragment : BaseFragment(R.layout.fragment_pet_list) {


    private lateinit var petLisViewModel: PetsViewModel

    private lateinit var petListAdapter: PetListAdapter

    private lateinit var binding: FragmentPetListBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPetListBinding.bind(view)

        petLisViewModel = ViewModelProvider(requireActivity())[PetsViewModel::class.java]

        initRecyclerView()
        observeViewModel()
        petLisViewModel.getPetList()

    }


    private fun initRecyclerView() {
        val itemClickedListener: (item: PetDetails) -> Unit =  { petItem->
            petLisViewModel.setSelectedPetItem(petItem)
            petItem.content_url?.let { contentUrl->
                petLisViewModel.getContentDesc(contentUrl)
            }
            findNavController().navigate(R.id.action_petListFragment_to_petDetailsFragment)
        }

        petListAdapter = PetListAdapter(itemClickedListener)

        binding.rvPets.adapter = petListAdapter
    }

    private fun observeViewModel() {
        petLisViewModel.petListResult.observe(viewLifecycleOwner){
            when(it){
                is DataResult.ERROR -> {
                    hideLoader()
                    it.errorCode?.let { errorCode->
                        if(errorCode == 0){
                            it.message?.let {  message->
                                showErrorDialog(message)
                            }
                        }else{
                            it.message?.let {  message->
                                showErrorDialog(message)
                            }
                        }
                    }
                }

                is DataResult.LOADING -> {
                    showLoader(getString(R.string.mssg_loading_data))
                }

                is DataResult.SUCCESS -> {
                    hideLoader()
                    if(::petListAdapter.isInitialized){
                        it.data?.let { dataList->
                            petListAdapter.setData(dataList)
                        }
                    }
                }
            }
        }
    }


}