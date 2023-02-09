package com.sepiainnovations.petsapp.views

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.sepiainnovations.petsapp.R
import com.sepiainnovations.petsapp.databinding.ActivityPetListBinding
import com.sepiainnovations.petsapp.viewmodel.PetListActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class PetListActivity: AppCompatActivity() {


    private lateinit var binding: ActivityPetListBinding

    lateinit var activityViewModel: PetListActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityViewModel = ViewModelProvider(this)[PetListActivityViewModel::class.java]

        activityViewModel.isWorkingHour.observe(this){ isWorkingHour->
            //setup the nav fragments only if its working hour
            if(isWorkingHour){
                setupNavGraph()
            }else{
                showAlertDialog()
            }
        }

        activityViewModel.checkWorkingHour(
            Calendar.getInstance(),
            START_DAY_OF_WEEK,
            END_DAY_OF_WEEK,
            START_TIME,
            END_TIME
        )
    }


    private fun setupNavGraph(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.pets_flow_graph)
        navController.graph = graph
    }

    private fun showAlertDialog(){
        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle("Non working hours")
        alertDialog.setMessage("Available only during working hours.\nMonday to Friday 9:00 to 18:00")
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Exit") { dialog, _ ->
            dialog.dismiss()
            finish()
        }
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    companion object{
        const val START_DAY_OF_WEEK: Int = Calendar.MONDAY
        const val END_DAY_OF_WEEK: Int = Calendar.FRIDAY
        const val START_TIME: Int = 9
        const val END_TIME: Int = 18
    }
}