package com.sepiainnovations.petsapp.model.data

import android.os.Parcel
import android.os.Parcelable

data class PetDetails(
    val title: String? = "",
    val image_url: String? = "",
    val content_url: String? = "",
    val date_added: String? = ""
):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(image_url)
        parcel.writeString(content_url)
        parcel.writeString(date_added)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PetDetails> {
        override fun createFromParcel(parcel: Parcel): PetDetails {
            return PetDetails(parcel)
        }

        override fun newArray(size: Int): Array<PetDetails?> {
            return arrayOfNulls(size)
        }
    }
}
