package com.shadows.mydindinnchallenge.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart(val dishes: ArrayList<Dish> = ArrayList(),
                val amount: Float = 0.0f
):Parcelable
