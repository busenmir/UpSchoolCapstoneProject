package com.example.capstoneproject.data.service

import com.example.capstoneproject.data.model.CRUDResponse
import com.example.capstoneproject.data.model.ProductsModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProductsAPIService {
    //https://canerture.com/api/ecommerce/get_products.php
    //https://canerture.com/api/ecommerce/get_sale_products_by_user.php
    private val BASE_URL = "https://canerture.com"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ProductsAPI::class.java)

    fun getProducts() : Observable<List<ProductsModel>> {
        return api.getProducts()
    }

    fun getDiscount() : Observable<List<ProductsModel>>{
        return api.getDiscounted()
    }

    fun getCategory() : Observable<List<String>>{
        return api.getCategories()
    }

    suspend fun addToBag(user: String, title: String, price: Double, description: String, category: String, image: String, rate: Double, count: Int, sale_state: Int): CRUDResponse {
        return api.addToBag(user,title,price,description,category,image,rate,count,sale_state)
    }

    suspend fun getBagProductsByUser(user: String) : List<ProductsModel>
    {
        return api.getBagProductsByUser(user)
    }

    suspend fun deleteFromBag(id : Int) : CRUDResponse{
        return api.deleteFromBag(id)
    }

    suspend fun getProductsbyCategory(category:String) : List<ProductsModel>
    {
        return api.getProductsbyCategory(category)
    }

}