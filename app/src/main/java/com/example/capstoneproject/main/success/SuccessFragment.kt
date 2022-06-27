package com.example.capstoneproject.main.success

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.FragmentSignInBinding
import com.example.capstoneproject.databinding.FragmentSuccessBinding
import com.example.capstoneproject.main.home.HomeFragment

class SuccessFragment : Fragment() {
    private var _binding: FragmentSuccessBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_success, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.LoadingView.setMinAndMaxFrame(30,50)
        binding.contuine.setOnClickListener {
            val intent = Intent(requireContext(),HomeFragment::class.java)
            startActivity(intent)
        }
    }
}