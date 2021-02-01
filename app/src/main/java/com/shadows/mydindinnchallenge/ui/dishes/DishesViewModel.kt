package com.shadows.mydindinnchallenge.ui.dishes

import com.airbnb.mvrx.*
import com.shadows.mydindinnchallenge.domain.DishesRepository

class DishesViewModel(state: DishesState): BaseMvRxViewModel<DishesState>(state) {
    private val repository = DishesRepository()
    init {
        setState {
            copy(dishes = Loading())
        }
        repository.getDishes()
            .execute {
                copy(dishes = it)
            }
        repository.getPromotions()
            .execute {
                copy(promotions = it)
            }
    }

    companion object : MvRxViewModelFactory<DishesViewModel, DishesState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: DishesState): DishesViewModel {
            return DishesViewModel(state)
        }
    }

}