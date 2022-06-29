package com.example.capstoneproject.main.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.databinding.FavoriteItemBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val favoriteList = ArrayList<ProductsModel>()

    var onItemClick: (Int) -> Unit = {}

    inner class ViewHolder(var binding : FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductsModel) {
            binding.apply {
                basketModel = item
                item.image.let {
                    Picasso.get().load(it).into(basketImageView)
                }
                FavDelete.setOnClickListener { view ->
                    makeSnackBar("Do you want to delete the product?", view)
                        .setAction("Yes") {
                            onItemClick(item.id!!)
                            makeSnackBar("Your product has been deleted.", it).show()
                        }.show()
                }
            }
        }
    }

    fun makeSnackBar(text: String, view: View) =
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            .apply {
                anchorView = view.rootView.findViewById(R.id.bottomNav)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val favoriteItemBinding =
            FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(favoriteItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun updateBasketList(newProductsList : List<ProductsModel>){
        favoriteList.clear()
        favoriteList.addAll(newProductsList)
        notifyDataSetChanged()
    }
}