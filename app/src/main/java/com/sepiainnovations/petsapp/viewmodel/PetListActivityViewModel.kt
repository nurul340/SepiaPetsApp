package com.sepiainnovations.petsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class PetListActivityViewModel @Inject constructor(): ViewModel() {



    private val _isWorkingHour: MutableLiveData<Boolean> = MutableLiveData()
    val isWorkingHour: LiveData<Boolean> = _isWorkingHour

    fun checkWorkingHour(
        currentDate: Calendar,
        startDayOfWeek: Int,
        endDayOfWeek: Int,
        startTime: Int,
        endTime: Int
    ) {

        val dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK)
        val hourOfDay = currentDate.get(Calendar.HOUR_OF_DAY)

        val isWorkingHour = dayOfWeek in startDayOfWeek..endDayOfWeek && hourOfDay in startTime..endTime
        _isWorkingHour.postValue(isWorkingHour)
    }

}