package com.example.capstoneproject.main.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.data.repository.ProductsRepository

class HomeFragmentViewModel(context: Context) : ViewModel() {
    private val modelRepo = ProductsRepository(context)

    private var discountedList = MutableLiveData<List<ProductsModel>>()
    val _discountedList: LiveData<List<ProductsModel>>
        get() = discountedList

    private var productsList = MutableLiveData<List<ProductsModel>>()
    val _productsList: LiveData<List<ProductsModel>>
        get() = productsList

    private var categoryList = MutableLiveData<List<String>>()
    val _categoryList: LiveData<List<String>>
    get() = categoryList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getProducts()
        getDiscounted()
        getCategory()
    }

    fun getProductsbyCategory(category : String){
        modelRepo.getProductsbyCategory(category)
        productsList = modelRepo.productsList
        _isLoading = modelRepo.isLoading
    }

    private fun getCategory(){
        modelRepo.category()
        categoryList = modelRepo.categoryList
        _isLoading = modelRepo.isLoading
    }
    private fun getProducts() {
        modelRepo.products()
        productsList = modelRepo.productsList
        _isLoading = modelRepo.isLoading
    }

    private fun getDiscounted() {
        modelRepo.discounted()
        discountedList = modelRepo.discountedList
        _isLoading = modelRepo.isLoading
    }
}