package com.shadows.mydindinnchallenge.domain

import com.shadows.mydindinnchallenge.data.models.Dish
import com.shadows.mydindinnchallenge.data.models.Promotion
import com.shadows.mydindinnchallenge.data.remote.mock.mockedDishes
import com.shadows.mydindinnchallenge.data.remote.mock.promotionList
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DishesRepository() {

    private val dishes = mutableListOf<Dish>()

    fun getDishes(): Observable<List<Dish>> = Observable.fromCallable<List<Dish>> {
        //mocking the server with a static list of dishes
        Thread.sleep(3500)
        dishes.addAll(mockedDishes)
        dishes
    }.subscribeOn(Schedulers.io())

    fun getPromotions(): Observable<List<Promotion>> = Observable.fromCallable<List<Promotion>> {
        Thread.sleep(3000)
        val promotions = mutableListOf<Promotion>()
        promotions.addAll(promotionList)
        promotions
    }.subscribeOn(Schedulers.io())

}