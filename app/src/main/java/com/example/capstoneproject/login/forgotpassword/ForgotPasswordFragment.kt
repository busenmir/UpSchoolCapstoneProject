package com.example.capstoneproject.login.forgotpassword
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.FragmentForgotPasswordBinding
import com.example.capstoneproject.login.LoginActivity
import com.google.android.material.snackbar.Snackbar

class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ForgotPasswordViewModel() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.forgotObject = this
        with(viewModel) {
            _resetPasswordResult.observe(viewLifecycleOwner) {
                if (it) {
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Snackbar.make(view, R.string.error_forgot, 1000).show()
                }
            }
        }

    }
    fun forgotButton(email: String) {
        if (email == "" ) {
            Toast.makeText(context, R.string.control_signin, Toast.LENGTH_SHORT).show()
        }else{
            viewModel.resetPassword(email)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}