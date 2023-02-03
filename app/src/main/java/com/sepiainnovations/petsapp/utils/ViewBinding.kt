package com.sepiainnovations.petsapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sepiainnovations.petsapp.R
import com.squareup.picasso.Picasso

object ViewBinding {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(view: ImageView, imageUrl: String?) {
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.placeholdet_pet_pow)
            .into(view)
    }
}