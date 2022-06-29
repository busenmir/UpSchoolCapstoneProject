package com.example.capstoneproject.main.home

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.databinding.DiscountedItemBinding
import com.squareup.picasso.Picasso

class DiscountedAdapter : RecyclerView.Adapter<DiscountedAdapter.ViewHolder>() {
    private val discountedList = ArrayList<ProductsModel>()
    class ViewHolder (var discountedItemBinding: DiscountedItemBinding) : RecyclerView.ViewHolder(discountedItemBinding.root) {
        fun bind(item : ProductsModel ){
            discountedItemBinding.apply {
                productsModel = item
                item.image.let {
                    Picasso.get().load(it).into(discountedImageView)
                }
                itemClickListener(item)
                discountedItemBinding.discountedPrice = String.format("%.2f",(item.price!! *50) / 100)
                disPriceText.paintFlags = disPriceText.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

        fun itemClickListener(item : ProductsModel){
            discountedItemBinding.discountedImageView.apply {
                setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToCardDetailFragment(item)
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val discountedItemBinding =
            DiscountedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(discountedItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(discountedList[position])
    }

    override fun getItemCount(): Int {
        return discountedList.size
    }

    fun updateProductsList(newdiscountedList : List<ProductsModel>){
        discountedList.clear()
        discountedList.addAll(newdiscountedList)
        notifyItemRangeInserted(0,newdiscountedList.size)
    }
}