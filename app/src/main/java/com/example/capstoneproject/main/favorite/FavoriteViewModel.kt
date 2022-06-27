package com.example.capstoneproject.main.favorite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.model.CRUDResponse
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.data.repository.ProductsRepository

class FavoriteViewModel (context: Context) : ViewModel(){
    private val productsRepo = ProductsRepository(context)

    var _favoritesBasket = MutableLiveData<List<ProductsModel>>()

    var _isLoading = MutableLiveData<Boolean>()

    var _crudModel = MutableLiveData<CRUDResponse>()

    init {
        getproductsFavorites()
    }

    private fun getproductsFavorites() {
        productsRepo.productsFavorites()
        _favoritesBasket = productsRepo.favoritesBasketList
        _isLoading = productsRepo.isLoading
    }

    fun deleteFavoritesFromBasket(Id: Int) {
        productsRepo.deleteFavoritesFromBasket(Id)
        getproductsFavorites()
    }
}