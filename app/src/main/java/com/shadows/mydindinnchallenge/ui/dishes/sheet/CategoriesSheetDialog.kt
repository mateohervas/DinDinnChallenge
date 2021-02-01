package com.shadows.mydindinnchallenge.ui.dishes.sheet

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.MvRxView
import com.airbnb.mvrx.MvRxViewModelStore
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.shadows.mydindinnchallenge.data.models.Dish
import com.shadows.mydindinnchallenge.databinding.SheetDialogDishesBinding
import com.shadows.mydindinnchallenge.ui.dishes.sheet.adapters.AddToCartListener
import com.shadows.mydindinnchallenge.ui.dishes.sheet.adapters.ViewPagerAdapter
import com.shadows.mydindinnchallenge.utils.binding.viewBinding

class CategoriesSheetDialog(val dishes: ArrayList<Dish>): BottomSheetDialogFragment(),AddToCartListener {

    private val binding by viewBinding(SheetDialogDishesBinding::bind)
    private val viewPager by lazy{
        ViewPagerAdapter(this,dishes)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = SheetDialogDishesBinding.inflate(inflater,container,false)
        return view.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categories = listOf("Pizza", "Sushi", "Drinks")
        binding.pagerContent.adapter = viewPager
        TabLayoutMediator(binding.tabCategories, binding.pagerContent) { tab, position ->
            tab.text = categories[position]
        }.attach()
        customizeDialog()

    }



    private fun customizeDialog(){

        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setOnShowListener {
            val d = dialog as BottomSheetDialog
            d.behavior.peekHeight = ((Resources.getSystem().displayMetrics.heightPixels)*0.45).toInt()
            d.behavior.isHideable = false
            d.behavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_EXPANDED ->{
                           //todo show fab
                        }
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                        }
                        else ->{}
                    }

                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }

            })

        }
    }


    companion object{
        fun newInstance(dishes: ArrayList<Dish>) = CategoriesSheetDialog(dishes)
    }

    override fun addToCart(dish: Int) {
       //todo
    }

}


