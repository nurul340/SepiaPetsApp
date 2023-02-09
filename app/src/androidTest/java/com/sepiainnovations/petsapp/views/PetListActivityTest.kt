package com.sepiainnovations.petsapp.views

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.rule.ActivityTestRule
import com.sepiainnovations.petsapp.views.PetListActivity.Companion.END_DAY_OF_WEEK
import com.sepiainnovations.petsapp.views.PetListActivity.Companion.END_TIME
import com.sepiainnovations.petsapp.views.PetListActivity.Companion.START_DAY_OF_WEEK
import com.sepiainnovations.petsapp.views.PetListActivity.Companion.START_TIME
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import org.junit.Assert.assertEquals
import com.sepiainnovations.petsapp.R

@RunWith(AndroidJUnit4::class)
class PetListActivityTest{


    @get:Rule
    val activityRule = ActivityTestRule(PetListActivity::class.java)


    //testing if pet list fragment is showing in case of working hour
    @Test
    fun testWorkingHourNavGraphSetup() {
        val fakeCalendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            set(Calendar.HOUR_OF_DAY, 9)
        }

        activityRule.activity.activityViewModel.checkWorkingHour(
            fakeCalendar,
            START_DAY_OF_WEEK,
            END_DAY_OF_WEEK,
            START_TIME,
            END_TIME
        )

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        runOnUiThread{
            navController.setGraph(R.navigation.pets_flow_graph)
        }
        assertEquals(navController.currentDestination?.id, R.id.petListFragment)
    }

    //testing if error message is showing in case of non-working hour
    @Test
    fun testNonWorkingHourAlertDialog() {
        val fakeCalendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
            set(Calendar.HOUR_OF_DAY, 22)
        }
        activityRule.activity.activityViewModel.checkWorkingHour(
            fakeCalendar,
            START_DAY_OF_WEEK,
            END_DAY_OF_WEEK,
            START_TIME,
            END_TIME
        )
        onView(withText("Available only during working hours.\nMonday to Friday 9:00 to 18:00")).check(matches(isDisplayed()))
    }

}