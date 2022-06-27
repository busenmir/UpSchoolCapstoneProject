package com.example.capstoneproject.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.databinding.ProductsItemBinding
import com.squareup.picasso.Picasso

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>(){
    private val produsctsList = ArrayList<ProductsModel>()
    /*
    var proFilterList = ArrayList<ProductsModel>()
    init {
        proFilterList = produsctsList
    }

     */
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
        holder.bind(produsctsList[position])
    }

    override fun getItemCount(): Int {
        return produsctsList.size
    }
    fun updateProductsList(newproductsList : List<ProductsModel>){
        produsctsList.clear()
        produsctsList.addAll(newproductsList)
        notifyDataSetChanged()
        //notifyItemRangeInserted(0,newproductsList.size)
    }
 /*
    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val searchText = constraint.toString().lowercase()
                proFilterList = if (searchText.isEmpty()) {
                    produsctsList
                } else {
                    val resultList = ArrayList<ProductsModel>()
                    for (row in produsctsList) {
                        row.title?.let { Name ->
                            row.category?.let { Category ->
                                    if (Name.lowercase().contains(searchText) ||
                                        Category.lowercase().contains(searchText)
                                    ) {
                                        resultList.add(row)
                                    }
                            }
                        }
                    }
                    resultList
                }

                val filterResults = FilterResults()
                filterResults.values = proFilterList

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                proFilterList = results?.values as ArrayList<ProductsModel>
                notifyItemRangeInserted(0,proFilterList.size)
            }
        }
    }

  */

}