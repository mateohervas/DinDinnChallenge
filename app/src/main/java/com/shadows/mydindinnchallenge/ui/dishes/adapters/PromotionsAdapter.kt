package com.shadows.mydindinnchallenge.ui.dishes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shadows.mydindinnchallenge.R
import com.shadows.mydindinnchallenge.data.models.Promotion
import com.shadows.mydindinnchallenge.databinding.PromotionRvItemBinding

class PromotionsAdapter(private val promotions: ArrayList<Promotion> = ArrayList()): RecyclerView.Adapter<PromotionsAdapter.ViewHolder>() {

    fun addAll(list: List<Promotion>){
        promotions.clear()
        promotions.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.promotion_rv_item,parent,false)
        val binding = PromotionRvItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(promotions[position])
    }

    override fun getItemCount(): Int = promotions.size
    inner class ViewHolder(private val binding: PromotionRvItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindView(promotion: Promotion){
            binding.apply {
                tvPromotionTitle.text = promotion.name
                Glide
                    .with(binding.root)
                    .load(promotion.imageUrl)
                    .centerCrop()
                    .into(binding.imgPromotion)
            }
        }

    }



}