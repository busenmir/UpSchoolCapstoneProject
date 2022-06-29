package com.example.capstoneproject.data.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.capstoneproject.data.model.LoginModel
import com.example.capstoneproject.data.model.ProductsModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LoginRepository {
    private var _isSignIn = MutableLiveData<Boolean>()

    private var _isSignUp = MutableLiveData<Boolean>()

    var isLoading = MutableLiveData<Boolean>()

    private var resetPasswordResult = MutableLiveData<Boolean>()

    var currentUserResult = MutableLiveData<Boolean>()
    var userModel = MutableLiveData<LoginModel>()

    private var auth = Firebase.auth
    private val db = Firebase.firestore

    fun getIsSignUp(): MutableLiveData<Boolean> {
        return _isSignUp
    }
    fun getIsSignIn(): MutableLiveData<Boolean> {
        return _isSignIn
    }

    fun getResetPasswordResult(): MutableLiveData<Boolean>{
        return resetPasswordResult
    }

    fun getCurrentUser(): MutableLiveData<Boolean>{
        return currentUserResult
    }

    fun resetPasswordClicked(eMail: String){
        auth.sendPasswordResetEmail(eMail).addOnSuccessListener {
            resetPasswordResult.value= true
            Log.d("SIGN IN", "Success")
        }.addOnFailureListener{
            resetPasswordResult.value = false
            Log.w("SIGN IN", "Failure")
        }
    }
    fun checkCurrentUser(){
        val currentUser = auth.currentUser
        if(currentUser!=null)
            currentUserResult.value=true
    }

    fun signInClicked(eMail: String, password: String) {

        auth.signInWithEmailAndPassword(eMail, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                _isSignIn.value = true
                Log.d("SIGN IN", "Success")
            } else {
                _isSignIn.value = false
                Log.w("SIGN IN", "Failure")
            }

        }
    }
    fun signUpClicked(eMail: String, password: String,nickname: String,phoneNumber: String,name: String) {

        auth.createUserWithEmailAndPassword(eMail, password).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                val currentUser = auth.currentUser
                currentUser?.let{ fb->
                    val user = hashMapOf(
                        "name" to name,
                        "email" to eMail,
                        "nickname" to nickname,
                        "phonenumber" to phoneNumber
                    )

                    db.collection("users").document(fb.uid)
                        .set(user)
                        .addOnSuccessListener {
                            _isSignUp.value = true
                            Log.d("SIGN UP", "Success")
                        }.addOnFailureListener{
                            _isSignUp.value = false
                            Log.w("SIGN UP", "Failure")
                        }

                }
            }else{
                _isSignUp.value=false
                Log.w("SIGN UP", "Failure")
            }

        }
            /*
            _isSignUp.value = true
            Log.d("SIGN UP", "Success")
        }.addOnFailureListener{
            _isSignUp.value = false
            Log.w("SIGN UP", "Failure")
        }

             */
    }

    fun getUserInfo() {
        isLoading.value = true
        auth.currentUser?.let { user ->

            val docRef = db.collection("users").document(user.uid)
            docRef.get()
                .addOnSuccessListener { document ->
                    isLoading.value = false
                    document?.let {
                        userModel.value = LoginModel(
                            user.email.orEmpty(),
                            document.get("nickname") as String,
                            document.get("phonenumber") as String,
                            document.get("name") as String,
                        )
                    }
                }
                .addOnFailureListener { exception ->
                    isLoading.value = false
                    Log.d(ContentValues.TAG, "get failed with ", exception)
                }
        }
    }
    fun signOut() {
        auth.signOut()
    }
}