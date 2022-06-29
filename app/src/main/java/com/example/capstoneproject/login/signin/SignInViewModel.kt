package com.example.capstoneproject.login.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.repository.LoginRepository

class SignInViewModel : ViewModel() {
    private var loginRepo = LoginRepository()

    private var _isSignIn = MutableLiveData<Boolean>()
    val isSignIn: LiveData<Boolean>
        get() = _isSignIn

    init {
        _isSignIn = loginRepo.getIsSignIn()
    }

    fun signIn(eMail: String, password: String) {
        loginRepo.signInClicked(eMail, password)
    }
}