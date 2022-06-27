package com.example.capstoneproject.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.model.LoginModel
import com.example.capstoneproject.data.model.ProductsModel
import com.example.capstoneproject.data.repository.LoginRepository

class ProfileViewModel() : ViewModel() {

    private val usersRepo = LoginRepository()

    private var _userInfo = MutableLiveData<LoginModel>()
    val userInfo: LiveData<LoginModel>
        get() = _userInfo

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        usersRepo.getUserInfo()
        _userInfo = usersRepo.userModel
        _isLoading = usersRepo.isLoading
    }


    fun signOut() {
        usersRepo.signOut()
    }
}
