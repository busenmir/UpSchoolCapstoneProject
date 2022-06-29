package com.example.capstoneproject.login.signin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

            isSignIn.observe(viewLifecycleOwner, {
                if (it) {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }   else {
                    Snackbar.make(view, R.string.wrong_email_password, 1000).show()
                }
            })
        }

    }

    fun signInButton(email: String, password: String) {
        viewModel.signIn(email, password)
    }

    fun signUpButton(){
        findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}