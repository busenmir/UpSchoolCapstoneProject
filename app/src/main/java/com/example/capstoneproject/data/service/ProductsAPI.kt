package com.example.capstoneproject.data.service

import com.example.capstoneproject.data.model.CRUDResponse
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.main.basket.BasketAdapter
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsAPI {
    //https://canerture.com
    // /api/ecommerce/get_products.php
    //https://canerture.com/api/ecommerce/get_sale_products_by_user.php

    @GET("api/ecommerce/get_products.php")
    fun getProducts(): Observable<List<ProductsModel>>

    @GET("api/ecommerce/get_sale_products.php")
    fun getDiscounted() : Observable<List<ProductsModel>>

    @GET("api/ecommerce/get_categories.php")
    fun getCategories(): Observable<List<String>>

    @POST("api/ecommerce/add_to_bag.php")
    @FormUrlEncoded
    suspend fun addToBag(
        @Field("user") user:String,
        @Field("title") title:String,
        @Field("price") price:Double,
        @Field("description") description:String,
        @Field("category") category:String,
        @Field("image") image:String,
        @Field("rate") rate:Double,
        @Field("count") count:Int,
        @Field("sale_state") sale_state:Int, ): CRUDResponse

    @POST("api/ecommerce/get_bag_products_by_user.php")
    @FormUrlEncoded
    suspend fun getBagProductsByUser(
        @Field("user") user:String,): List<ProductsModel>

    @POST("api/ecommerce/delete_from_bag.php")
    @FormUrlEncoded
    suspend fun deleteFromBag(
        @Field("id") id:Int, ): CRUDResponse

    @POST("api/ecommerce/get_products_by_category.php")
    @FormUrlEncoded
    suspend fun getProductsbyCategory(
        @Field("category") category:String,): List<ProductsModel>

    @POST("api/ecommerce/search_product.php")
    @FormUrlEncoded
    suspend fun searchProducts(@Field("query") word:String):List<ProductsModel>

}