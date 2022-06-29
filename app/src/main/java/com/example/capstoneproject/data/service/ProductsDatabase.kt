package com.example.capstoneproject.data.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstoneproject.data.model.ProductsModel

@Database(
    entities = [ProductsModel::class], version = 1
)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun getProductDao() : ProductsRoomDAO

    companion object{
        @Volatile
        private var instance : ProductsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ProductsDatabase::class.java,
                "products_db.db"
                )
                .allowMainThreadQueries()
                .build()
        }

    }