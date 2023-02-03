package com.sepiainnovations.petsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PetListActivityViewModel @Inject constructor(): ViewModel() {

    private val _isWorkingHour: MutableLiveData<Boolean> = MutableLiveData()
    val isWorkingHour: LiveData<Boolean> = _isWorkingHour

    init {
        isWorkingHour()
    }


    private fun isWorkingHour(){
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        if (dayOfWeek in Calendar.MONDAY..Calendar.FRIDAY && hourOfDay in 9..18) {
            _isWorkingHour.postValue(true)
        } else {
            _isWorkingHour.postValue(false)
        }
    }



}