package com.shadows.mydindinnchallenge.ui.dishes.sheet.adapters

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shadows.mydindinnchallenge.R
import com.shadows.mydindinnchallenge.data.models.Dish
import com.shadows.mydindinnchallenge.databinding.DishRvItemBinding

class CategoryRecyclerAdapter(private val dishes:ArrayList<Dish> = ArrayList(),val addToCartListener: AddToCartListener): RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder>() {


    fun addAll(list: List<Dish>){
        dishes.clear()
        dishes.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_rv_item,parent,false)
        val binding = DishRvItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(dishes[position])
    }

    override fun getItemCount(): Int = dishes.size

    inner class ViewHolder(private val binding: DishRvItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bindView(dish: Dish){
            binding.apply {
                tvDishName.text = dish.name
                btnAddToCart.text = "${dish.price} usd"
                tvDishDescription.text = dish.description
                tvDishExtraInfo.text = dish.dimens
                Glide
                    .with(binding.root)
                    .load(dish.imageUrl)
                    .centerCrop()
                    .into(binding.imgDish)

                btnAddToCart.setOnClickListener {
                    addToCartListener.addToCart(dish.id)
                    btnAddToCart.text = binding.root.context.getString(R.string.added_1)
                    //With add to cart function this would be eliminated and be set to changed when the adding operation returns a success
                    Handler().postDelayed({
                        btnAddToCart.text = "${dish.price} usd"
                    }, 500)
                }
            }
        }
    }
}