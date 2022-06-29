package com.example.capstoneproject.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LoginRepository {
    private var _isSignIn = MutableLiveData<Boolean>()

    private var _isSignUp = MutableLiveData<Boolean>()

    private var resetPasswordResult = MutableLiveData<Boolean>()

    private var currentUserResult = MutableLiveData<Boolean>()
    private var auth : FirebaseAuth? = null

    init {
        auth = Firebase.auth
    }


    fun getIsSignUp(): MutableLiveData<Boolean> {
        return _isSignUp
    }
    fun getIsSignIn(): MutableLiveData<Boolean> {
        return _isSignIn
    }

    fun getResetPasswordResult() : MutableLiveData<Boolean>{
        return resetPasswordResult
    }
    fun signInClicked(eMail: String, password: String) {

        auth?.signInWithEmailAndPassword(eMail, password)?.addOnSuccessListener {
            _isSignIn.value = true
            Log.d("SIGN IN", "Success")
        }?.addOnFailureListener{
            _isSignIn.value = false
            Log.d("SIGN IN", "Failure")
        }
  /*
        addOnCompleteListener { task ->

            if (task.isSuccessful) {
                _isSignIn.value = true
                Log.d("SIGN IN", "Success")
            } else {
                _isSignIn.value = false
                Log.w("SIGN IN", "Failure", task.exception)
            }

        }

   */
    }
    fun signUpClicked(eMail: String, password: String) {

        auth?.createUserWithEmailAndPassword(eMail, password)?.addOnSuccessListener {
            _isSignUp.value = true
            Log.d("SIGN UP", "Success")
        }?.addOnFailureListener{
            _isSignUp.value = false
            Log.w("SIGN UP", "Failure")
        }
       /*
        addOnCompleteListener { task ->

            if (task.isSuccessful) {
                _isSignUp.value = true
                Log.d("SIGN UP", "Success")
            } else {
                _isSignUp.value = false
                Log.w("SIGN UP", "Failure", task.exception)
            }

        }
        */
    }

    fun checkCurrentUser(){
        val currentUser = auth?.currentUser
        if(currentUser!=null)
            currentUserResult.value=true
    }

    fun resetPassword(eMail: String){
        auth?.sendPasswordResetEmail(eMail)?.addOnSuccessListener {
            resetPasswordResult.value=true
        }?.addOnFailureListener{
            resetPasswordResult.value=false
        }
    }
}