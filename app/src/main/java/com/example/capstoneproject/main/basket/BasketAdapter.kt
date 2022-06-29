package com.example.capstoneproject.main.basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.databinding.BasketItemBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    private val basketList = ArrayList<ProductsModel>()

    var onItemClick: (Int) -> Unit = {}

    inner class ViewHolder(var basketItemBinding: BasketItemBinding) : RecyclerView.ViewHolder(basketItemBinding.root) {
        fun bind(item: ProductsModel) {
            basketItemBinding.apply {
                basketModel = item
                item.image.let {
                    Picasso.get().load(it).into(basketImageView)
                }
                BasketDelete.setOnClickListener { view ->
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
        val basketItemBinding =
            BasketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(basketItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(basketList[position])
    }

    override fun getItemCount(): Int {
        return basketList.size
    }

    fun updateBasketList(newproductsList : List<ProductsModel>){
        basketList.clear()
        basketList.addAll(newproductsList)
        notifyDataSetChanged()
        //notifyItemRangeInserted(0,newproductsList.size)
    }
}