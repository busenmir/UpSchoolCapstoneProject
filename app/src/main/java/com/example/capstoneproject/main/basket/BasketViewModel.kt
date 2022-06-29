package com.example.capstoneproject.main.basket

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.model.CRUDResponse
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.data.repository.ProductsRepository

class BasketViewModel (context : Context) : ViewModel() {
    private var productsRepo = ProductsRepository(context)

    var isListBasket = MutableLiveData<List<ProductsModel>>()

    var isDeleteBasket = MutableLiveData<CRUDResponse>()

    fun deleteProductBasket(id :Int,user: String){
        productsRepo.deleteProductBasket(id)
        isDeleteBasket=productsRepo.isProDeleteBasket
        ListToBasket(user)
    }

    fun ListToBasket(user: String){
        productsRepo.ListToBasket(user)
        isListBasket=productsRepo.isListBasket
    }
}