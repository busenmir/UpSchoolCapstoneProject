package com.example.capstoneproject.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.databinding.CategoryItemBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val categoryList = ArrayList<String>()

    var onCategoryItemClick: (String) -> Unit = {}
    inner class ViewHolder(var binding : CategoryItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind (item: String){
            binding.category = item
            binding.categoryCard.setOnClickListener {
                onCategoryItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val categoryBinding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(categoryBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun updateProductsList(newproductsList : List<String>){
        categoryList.clear()
        categoryList.addAll(newproductsList)
        notifyDataSetChanged()
        //notifyItemRangeInserted(0,newproductsList.size)
    }

}