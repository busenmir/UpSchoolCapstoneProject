package com.example.capstoneproject.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "productroomdatabase")
@Parcelize
data class ProductsModel(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    @SerializedName("id")
    val id:Int? ,

    @ColumnInfo("user")
    @SerializedName("user")
    val user:String?,

    @ColumnInfo("title")
    @SerializedName("title")
    val title:String? ,

    @ColumnInfo("price")
    @SerializedName("price")
    val price:Double? ,

    @ColumnInfo("description")
    @SerializedName("description")
    val description:String? ,

    @ColumnInfo("category")
    @SerializedName("category")
    val category:String? ,

    @ColumnInfo("image")
    @SerializedName("image")
    val image:String? ,

    @ColumnInfo("rate")
    @SerializedName("rate")
    val rate:Double? ,

    @ColumnInfo("count")
    @SerializedName("count")
    val count:Int? ,

    @ColumnInfo("sale_state")
    @SerializedName("sale_state")
    val sale_state:Int? ,

) : Parcelable
