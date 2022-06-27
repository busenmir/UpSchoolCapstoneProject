package com.example.capstoneproject.login.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { SignUpViewModel() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUpFragmentObject = this
        initObservers()
    }

    private fun initObservers() {

        with(binding) {

            with(viewModel) {

                isInfosValid.observe(viewLifecycleOwner) {

                    if (it.not()) Snackbar.make(
                        requireView(),
                        R.string.incomplete_information_entered,
                        1000
                    ).show()

                }

                isValidMail.observe(viewLifecycleOwner) {

                    if (it.not()) {
                        emailInputLayout.error = getString(R.string.invalid_mail)
                    } else {
                        emailInputLayout.error = ""
                    }

                }

                isSignUp.observe(viewLifecycleOwner) {

                    if (it) {
                        Snackbar.make(requireView(), R.string.sign_up_snack_text, 3000).show()
                        clearFields()
                        val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
                        findNavController().navigate(action)
                    } else {
                        emailInputLayout.error = getString(R.string.registered_mail)
                    }

                }
            }
        }
    }

    fun signUpButton(email: String, password: String,nickname: String,
                     phoneNumber: String,
                     name: String) {
        viewModel.signUpClicked(email, password,nickname,phoneNumber,name)
    }

    private fun clearFields() {
        with(binding) {
            emailEditText.setText("")
            emailInputLayout.error = ""
            passwordEditText.setText("")
            passwordInputLayout.error = ""
            phoneEditText.setText("")
            phoneInputLayout.error = ""
            nickEditText.setText("")
            nickInputLayout.error = ""
            nameEditText.setText("")
            nameInputLayout.error = ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}