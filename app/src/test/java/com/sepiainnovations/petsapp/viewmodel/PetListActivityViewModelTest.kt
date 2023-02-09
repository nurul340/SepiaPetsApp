package com.sepiainnovations.petsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sepiainnovations.petsapp.utils.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*


class PetListActivityViewModelTest{
    private lateinit var viewModel: PetListActivityViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = PetListActivityViewModel()
    }

    @Test
    fun when_current_time_inside_working_hour_isWorkingHour_should_be_true() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 12)

        viewModel.checkWorkingHour(calendar, Calendar.MONDAY, Calendar.FRIDAY, 6, 18)

        Assert.assertEquals(true, viewModel.isWorkingHour.getOrAwaitValue())

    }

    @Test
    fun when_current_time_outside_working_hour_isWorkingHour_should_be_false() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 12)

        viewModel.checkWorkingHour(calendar, Calendar.MONDAY, Calendar.FRIDAY, 6, 18)

        Assert.assertEquals(false, viewModel.isWorkingHour.getOrAwaitValue())

    }
}