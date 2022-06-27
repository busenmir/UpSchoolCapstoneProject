package com.example.capstoneproject.data.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstoneproject.data.model.ProductsModel

@Dao
interface ProductsRoomDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProductsFavorites(productsModel: ProductsModel)

    @Query("SELECT * FROM productroomdatabase")
    fun getProductsFavorites(): List<ProductsModel>?

    @Query("SELECT title FROM productroomdatabase")
    fun getProductstitleFavorites(): List<String>?

    @Query("DELETE FROM productroomdatabase WHERE id = :idInput")
    fun deleteProductsWithId(idInput: Int)

    @Query("DELETE FROM productroomdatabase")
    fun clearFavorites()

    @Query("SELECT * FROM productroomdatabase WHERE id=:id")
    fun getProductById(id:Int):ProductsModel
}