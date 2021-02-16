package com.example.shoppinglists.data.db.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Product(
    val name: String,
    val quantity: Int,
    var isBought: Boolean
): Serializable, Parcelable