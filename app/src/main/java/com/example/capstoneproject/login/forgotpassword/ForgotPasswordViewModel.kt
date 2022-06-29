package com.example.capstoneproject.login.forgotpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.repository.LoginRepository

class ForgotPasswordViewModel : ViewModel() {
    private var loginRepo = LoginRepository()

    private var resetPasswordResult = MutableLiveData<Boolean>()
    val _resetPasswordResult: LiveData<Boolean>
        get() = resetPasswordResult

    init {
        resetPasswordResult = loginRepo.getResetPasswordResult()

    }

    fun resetPassword(eMail: String) {
        loginRepo.resetPasswordClicked(eMail)
    }
}