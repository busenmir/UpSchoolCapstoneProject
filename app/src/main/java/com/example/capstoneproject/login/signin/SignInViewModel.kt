package com.example.capstoneproject.login.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.repository.LoginRepository

class SignInViewModel : ViewModel() {
    private var usersRepo = LoginRepository()

    private var _isSignIn = MutableLiveData<Boolean>()
    val isSignIn: LiveData<Boolean>
        get() = _isSignIn

    private var currentUserResult = MutableLiveData<Boolean>()
    val isCurrentUser: LiveData<Boolean>
        get() = currentUserResult

    init {
        _isSignIn = usersRepo.getIsSignIn()

    }
    fun signIn(eMail: String, password: String) {
        usersRepo.signInClicked(eMail, password)
    }

    fun currentUser(){
        usersRepo.checkCurrentUser()
        currentUserResult = usersRepo.getCurrentUser()
    }
}