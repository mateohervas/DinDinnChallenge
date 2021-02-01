package com.shadows.mydindinnchallenge.ui.dishes.sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import com.shadows.mydindinnchallenge.data.models.Dish
import com.shadows.mydindinnchallenge.databinding.CategoryFragmentBinding
import com.shadows.mydindinnchallenge.ui.dishes.DishesViewModel
import com.shadows.mydindinnchallenge.ui.dishes.sheet.adapters.AddToCartListener
import com.shadows.mydindinnchallenge.ui.dishes.sheet.adapters.CategoryRecyclerAdapter
import com.shadows.mydindinnchallenge.utils.binding.viewBinding

class CategoryFragment(val dishes: ArrayList<Dish>):BaseMvRxFragment(),AddToCartListener {

    private val viewModel: DishesViewModel by activityViewModel()

    private val binding by viewBinding(CategoryFragmentBinding::bind)

    private val rvAdapter by lazy {
        CategoryRecyclerAdapter(addToCartListener = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = CategoryFragmentBinding.inflate(inflater,container,false)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        rvAdapter.addAll(dishes)
    }

    override fun invalidate(){


    }

    private fun setUpRecyclerView(){

        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvDishes.layoutManager = linearLayoutManager
        binding.rvDishes.adapter = rvAdapter

    }

    companion object{
        fun newInstance(dishes:ArrayList<Dish>) = CategoryFragment(dishes)
    }

    override fun addToCart(dish: Int) {
        //todo add to cart logic
    }


}