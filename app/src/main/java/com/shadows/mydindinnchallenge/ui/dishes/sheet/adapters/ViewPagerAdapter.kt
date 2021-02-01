package com.shadows.mydindinnchallenge.ui.dishes.sheet.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.shadows.mydindinnchallenge.data.models.Dish
import com.shadows.mydindinnchallenge.ui.dishes.sheet.CategoryFragment

class ViewPagerAdapter(fragment:Fragment, val dishes: ArrayList<Dish>): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        val pizza =  ArrayList<Dish>().apply {
           dishes.filterIndexedTo(this, { index: Int, _: Dish ->
               index<3
           })
        }
        val sushi = ArrayList<Dish>().apply {
            dishes.filterIndexedTo(this, { index: Int, _: Dish ->
                index in 3..4
            })
        }
        val drinks = ArrayList<Dish>().apply {
            dishes.filterIndexedTo(this, { index: Int, _: Dish ->
                index>4 && index<dishes.size
            })
        }

        return when(position){
            0-> {
                CategoryFragment.newInstance(pizza)
            }
            1->CategoryFragment.newInstance(sushi)
            else ->CategoryFragment.newInstance(drinks)
        }
    }
}
