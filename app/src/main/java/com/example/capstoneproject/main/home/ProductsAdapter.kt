package com.example.capstoneproject.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.databinding.ProductsItemBinding
import com.squareup.picasso.Picasso

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>(){
    private val productsList = ArrayList<ProductsModel>()
    class ViewHolder (var productsItemBinding: ProductsItemBinding) : RecyclerView.ViewHolder(productsItemBinding.root) {
        fun bind(item : ProductsModel){
            productsItemBinding.apply {
                productsModel = item
                item.image.let {
                    Picasso.get().load(it).into(productsImageView)
                }
                productsImageView.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToCardDetailFragment(item)
                    it.findNavController().navigate(action)
                }
                addBasketImage.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToCardDetailFragment(item)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val productsItemBinding =
            ProductsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(productsItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productsList[position])
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
    fun updateProductsList(newproductsList : List<ProductsModel>){
        productsList.clear()
        productsList.addAll(newproductsList)

    //notifyDataSetChanged()
    //notifyItemRangeInserted(0,newproductsList.size)
    }
}