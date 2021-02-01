package com.shadows.mydindinnchallenge.ui.dishes

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.shadows.mydindinnchallenge.data.models.Cart
import com.shadows.mydindinnchallenge.data.models.Dish
import com.shadows.mydindinnchallenge.data.models.Promotion

data class DishesState(
    val dishes: Async<List<Dish>> = Uninitialized,
    val promotions: Async<List<Promotion>> = Uninitialized):MvRxState
