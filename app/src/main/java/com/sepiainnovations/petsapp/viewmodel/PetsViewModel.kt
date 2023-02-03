package com.sepiainnovations.petsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sepiainnovations.petsapp.model.data.PetDetails
import com.sepiainnovations.petsapp.model.repo.PetsRepository
import com.sepiainnovations.petsapp.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class PetsViewModel @Inject constructor(
    private val petsRepository: PetsRepository
    ) : ViewModel() {



    private val _petListResult: MutableLiveData<DataResult<List<PetDetails>>> = MutableLiveData()
    val petListResult: LiveData<DataResult<List<PetDetails>>> = _petListResult

    private val _selectedPetItem: MutableLiveData<PetDetails> = MutableLiveData()
    val selectedPetItem: LiveData<PetDetails> = _selectedPetItem

    private val _contentDesc: MutableLiveData<DataResult<String>> = MutableLiveData()
    val contentDesc: LiveData<DataResult<String>> = _contentDesc


    fun getPetList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = petsRepository.retrievePetList()
            if(list.isNullOrEmpty()){
                _petListResult.postValue(DataResult.ERROR(101, "No data found"))
            }else{
                _petListResult.postValue(DataResult.SUCCESS(list))
            }
        }
    }

    fun getContentDesc(contentUrl: String){
        viewModelScope.launch(Dispatchers.IO){
            val result = petsRepository.retrieveUsingSomething(contentUrl)
            if(result == null){
                _contentDesc.postValue(DataResult.ERROR(101, "Failed to get content"))
            }else {
                _contentDesc.postValue(DataResult.SUCCESS(result))
            }
        }
    }

    fun setSelectedPetItem(petItem: PetDetails){
        _selectedPetItem.postValue(petItem)
    }




}