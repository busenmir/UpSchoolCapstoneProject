package com.example.capstoneproject.login.signin

import android.R.attr.endColor
import android.R.attr.startColor
import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.capstoneproject.MainActivity
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar


class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { SignInViewModel() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInObject = this


        with(viewModel) {
            this.currentUser()
            isCurrentUser.observe(viewLifecycleOwner){
                if(it){
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            isSignIn.observe(viewLifecycleOwner) {
                if (it) {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Snackbar.make(view, R.string.wrong_email_password, 1000).show()
                }
            }
        }

        binding.textView2.setOnClickListener {
            val action = SignInFragmentDirections.actionSignInFragmentToForgotPasswordFragment()
            findNavController().navigate(action)
        }

    }

    fun signInButton(email: String, password: String) {
        if (email == "" || password == "") {
            Toast.makeText(context, R.string.control_signin, Toast.LENGTH_SHORT).show()
        } else {
            viewModel.signIn(email, password)
        }
    }

    fun signUpButton() {
        val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}