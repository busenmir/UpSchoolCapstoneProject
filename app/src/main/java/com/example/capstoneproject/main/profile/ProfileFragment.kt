package com.example.capstoneproject.main.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.FragmentProfileBinding
import com.example.capstoneproject.login.LoginActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ProfileViewModel() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileFragmentObject = this
        with(binding) {

            signOut.setOnClickListener {
                viewModel.signOut().also {
                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                    startActivity(intent)
                }
            }
            viewModel.userInfo.observe(viewLifecycleOwner) {
                textViewName.text = it.name
                textViewNick.text = it.nickname
                textViewUserphone.text = it.phoneNumber
                textViewEmail.text=it.email
                textViewUserNameSurname.text=it.name
            }
            viewModel.isLoading.observe(viewLifecycleOwner){
                if(!it) LoadingView.visibility = View.GONE
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}