package com.shadows.mydindinnchallenge.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dish(
    val id: Int,
    val name: String,
    val imageUrl:String,
    val description: String,
    val price: Float,
    val dimens: String
):Parcelable
