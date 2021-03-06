package com.example.capstoneproject.login.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.data.repository.LoginRepository

class SignUpViewModel : ViewModel() {

    private var loginRepo = LoginRepository()

    private var _isInfosValid = MutableLiveData<Boolean>()
    val isInfosValid: LiveData<Boolean>
        get() = _isInfosValid
    private var _isValidMail = MutableLiveData<Boolean>()
    val isValidMail: LiveData<Boolean>
        get() = _isValidMail
    private var _isPasswordMatch = MutableLiveData<Boolean>()
    val isPasswordMatch: LiveData<Boolean>
        get() = _isPasswordMatch
    private var _isSignUp = MutableLiveData<Boolean>()
    val isSignUp: LiveData<Boolean>
        get() = _isSignUp

    init {
        _isSignUp = loginRepo.getIsSignUp()
    }

    fun signUpClicked(
        eMail: String,
        password: String,
        nickname: String,
        phoneNumber: String,
        name:String,
    ) {

        if (eMail.isEmpty() || password.isEmpty() || nickname.isEmpty() || phoneNumber.isEmpty() || name.isEmpty()) {

            _isInfosValid.value = false

        } else {

            if (Patterns.EMAIL_ADDRESS.matcher(eMail).matches().not()) {

                _isValidMail.value = false

            } else {
                    loginRepo.signUpClicked(eMail, password,nickname,phoneNumber,name)
                }
            }
        }

    }
