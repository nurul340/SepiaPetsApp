package com.sepiainnovations.petsapp.views

import androidx.fragment.app.Fragment

@Suppress("UNUSED_PARAMETER")
open class BaseFragment(layoutRes: Int): Fragment(layoutRes) {

    fun showErrorDialog(message: String?){
        //TODO::
    }

    fun showLoader(message: String){
        //TODO::
    }

    fun hideLoader(){
        //TODO::
    }
}