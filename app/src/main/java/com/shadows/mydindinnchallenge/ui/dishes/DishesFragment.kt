package com.shadows.mydindinnchallenge.ui.dishes

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.mvrx.*
import com.google.android.material.tabs.TabLayoutMediator
import com.shadows.mydindinnchallenge.data.models.Dish
import com.shadows.mydindinnchallenge.databinding.MainFragmentBinding
import com.shadows.mydindinnchallenge.ui.MainActivity
import com.shadows.mydindinnchallenge.ui.dishes.adapters.PromotionsAdapter
import com.shadows.mydindinnchallenge.ui.dishes.sheet.CategoriesSheetDialog
import com.shadows.mydindinnchallenge.utils.binding.viewBinding

class DishesFragment: BaseMvRxFragment() {


    private val viewModel: DishesViewModel by activityViewModel()
    private val binding by viewBinding(MainFragmentBinding::bind)
    private var bottomSheetDialog: CategoriesSheetDialog? = null
    private val promotionsAdapter by lazy {
        PromotionsAdapter()
    }
    private val handler by lazy {
        Handler()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = MainFragmentBinding.inflate(inflater,container,false)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPromotionsRecyclerView()

    }

    override fun invalidate() {
        getDishes()
        getPromotions()
    }

    private fun getPromotions(){
        withState(viewModel){
            when(it.promotions){
                is Success ->{
                    val promotions = it.promotions.invoke()
                    promotionsAdapter.addAll(promotions)
                    handler.postDelayed({
                        binding.rvPromotions.setCurrentItem(binding.rvPromotions.currentItem+1,true)
                    },3000)
                }
                is Error -> {
                    Log.d("Shadow", "failed promotions")
                }
                else-> {}
            }
        }
    }

    private fun getDishes(){
        withState(viewModel){
            when(it.dishes){
                is Loading ->{
                    (activity as MainActivity).isLoading(true)
                }
                is Success ->{
                    (activity as MainActivity).isLoading(false)
                    val dishes = it.dishes.invoke()
                    showDialog(dishes)
                }
                is Error -> {
                    (activity as MainActivity).isLoading(false)
                }
                else-> {}
            }
        }
    }

    private fun showDialog(dishes: List<Dish>){
        if(bottomSheetDialog==null){
            bottomSheetDialog = CategoriesSheetDialog.newInstance(dishes as ArrayList<Dish>)
            bottomSheetDialog?.show(activity!!.supportFragmentManager,"")
        }

    }
    private fun setUpPromotionsRecyclerView(){
        binding.rvPromotions.adapter = promotionsAdapter
        TabLayoutMediator(binding.tabLayout, binding.rvPromotions) { _, _ ->
        }.attach()
        autoScrollPromotions()
    }

    private fun autoScrollPromotions(){

        binding.rvPromotions.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position<promotionsAdapter.itemCount){
                    if(position == promotionsAdapter.itemCount-1){
                        handler.postDelayed({
                            binding.rvPromotions.setCurrentItem(0,true)
                        },3000)
                    }else{
                        handler.postDelayed({
                            binding.rvPromotions.setCurrentItem(position+1,true)
                        },3000)
                    }
                }

            }
        })
    }

    companion object {
        fun newInstance() = DishesFragment()
    }



}