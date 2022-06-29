package com.example.capstoneproject.data.repository
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.capstoneproject.data.model.CRUDResponse
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.data.service.ProductsAPIService
import com.example.capstoneproject.data.service.ProductsDatabase
import com.example.capstoneproject.data.service.ProductsRoomDAO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

class ProductsRepository(context: Context) {

    var productsList = MutableLiveData<List<ProductsModel>>()

    var discountedList = MutableLiveData<List<ProductsModel>>()

    var isProAddedBasket = MutableLiveData<CRUDResponse>()

    var categoryList = MutableLiveData<List<String>>()

    var isListBasket = MutableLiveData<List<ProductsModel>>()

    var isProDeleteBasket = MutableLiveData<CRUDResponse>()

    var getProductsbyCategory = MutableLiveData<ProductsModel>()

    var favoritesBasketList = MutableLiveData<List<ProductsModel>>()

    var isLoading = MutableLiveData<Boolean>()

    private val ProductsRoomDAO: ProductsRoomDAO =
        ProductsDatabase.invoke(context).getProductDao()

    var job : Job?=null

    private val productsAPIService = ProductsAPIService()

    val disposable = CompositeDisposable()

    fun discounted(){
        isLoading.value = true
        disposable.add(
            productsAPIService.getDiscount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //succes
                    discountedList.value=it
                    isLoading.value = true
                },{
                    //error
                    isLoading.value = false
                    println("HATALI")
                    Log.w("Discounted","ERROR",it)
                })
        )
    }

    fun products(){
        isLoading.value = true
        disposable.add(
            productsAPIService.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //succes
                    productsList.value=it
                    isLoading.value = true
                },{
                    //error
                    isLoading.value = false
                    Log.w("Products","ERROR",it)
                })
        )
    }

    fun category(){
        isLoading.value = true
        disposable.add(
            productsAPIService.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //succes
                    categoryList.value=it
                    isLoading.value = true
                },{
                    //error
                    isLoading.value = false
                    Log.w("Category","ERROR",it)
                })
        )
    }

    fun searchProducts(word:String){
        isLoading.value=true
        job= CoroutineScope(Dispatchers.IO).launch {
            val list_respone = productsAPIService.searchProducts(word)
            withContext(Dispatchers.Main) {
                productsList.value = list_respone
                isLoading.value = false
            }
        }
    }

    fun getProductsbyCategory(category:String){
        isLoading.value=true
        job= CoroutineScope(Dispatchers.IO).launch {
            val list_respone=productsAPIService.getProductsbyCategory(category)
            withContext(Dispatchers.Main){
                productsList.value=list_respone
                isLoading.value = false
            }
        }

    }

    fun addProductToBasket(user: String,
                           title: String,
                           price: Double,
                           description: String,
                           category: String,
                           image: String,
                           rate: Double,
                           count: Int,
                           sale_state:Int){
        isLoading.value = true
        job= CoroutineScope(Dispatchers.IO).launch {
            val crud_respone=productsAPIService.addToBag(user,title,price,description,category,image,rate,count,sale_state)
            withContext(Dispatchers.Main){
                isProAddedBasket.value=crud_respone
                isLoading.value = false
            }
        }
    }

    fun deleteProductBasket(id:Int){
        job= CoroutineScope(Dispatchers.IO).launch {
            val crud_delete_respone=productsAPIService.deleteFromBag(id)
            withContext(Dispatchers.Main){
                isProDeleteBasket.value=crud_delete_respone
            }
        }
    }

    fun ListToBasket(user: String){
        job= CoroutineScope(Dispatchers.IO).launch {
            val list_respone=productsAPIService.getBagProductsByUser(user)
            withContext(Dispatchers.Main){
                isListBasket.value=list_respone
            }
        }
    }
    fun productsFavorites() {
        isLoading.value = true

        ProductsRoomDAO.getProductsFavorites()?.let {
            favoritesBasketList.value = it
            isLoading.value = false
        } ?: run {
            isLoading.value = false
        }
    }

    fun addProductsToFavorites(productsModel: ProductsModel) {
        ProductsRoomDAO.addProductsFavorites(productsModel)
    }

    fun deleteFavoritesFromBasket(id: Int) {
        ProductsRoomDAO.deleteProductsWithId(id)
    }
}