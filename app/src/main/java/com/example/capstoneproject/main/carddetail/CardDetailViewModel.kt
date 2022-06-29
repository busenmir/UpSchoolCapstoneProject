package com.example.capstoneproject.main.carddetail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.model.CRUDResponse
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.data.repository.LoginRepository
import com.example.capstoneproject.data.repository.ProductsRepository

class CardDetailViewModel(context : Context) : ViewModel() {
    private var productsRepo = ProductsRepository(context)

    var isProAddedBasket = MutableLiveData<CRUDResponse>()

    fun addProductToBasket(user: String,
                           title: String,
                           price: Double,
                           description: String,
                           category: String,
                           image: String,
                           rate: Double,
                           count: Int,
                           sale_state:Int){
        productsRepo.addProductToBasket(user, title, price, description, category, image, rate, count, sale_state)
        isProAddedBasket = productsRepo.isProAddedBasket
    }
    fun addProductsToFavorites(productsModel: ProductsModel){
        productsRepo.addProductsToFavorites(productsModel)
    }
}