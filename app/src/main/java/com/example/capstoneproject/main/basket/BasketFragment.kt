package com.example.capstoneproject.main.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.FragmentBasketBinding
import com.example.capstoneproject.main.carddetail.CardDetailViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class BasketFragment : Fragment() {
    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BasketViewModel(requireContext()) }
    private val basketAdapter by lazy { BasketAdapter() }

    private var auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        with(binding) {
            BasketRecycleView.setHasFixedSize(true)
            basketAdapter.onItemClick = {
                auth.currentUser?.email?.let { email ->
                    viewModel.deleteProductBasket(it,email)
                }
            }
        }
    }

    private fun initObservers() {
        with(binding) {
            with(viewModel) {
                ListToBasket(auth.currentUser!!.email!!)
                isListBasket.observe(viewLifecycleOwner) { list ->
                    BasketRecycleView.apply {
                        setHasFixedSize(true)
                        adapter = basketAdapter.also {
                            it.updateBasketList(list)
                        }
                        if (list.isEmpty()) {
                            goToPayButton.setOnClickListener {
                                Toast.makeText(context, "Basket is Empty", Toast.LENGTH_LONG).show()
                            }
                        }else{
                            goToPayButton.setOnClickListener {
                                it.findNavController()
                                    .navigate(R.id.action_basketFragment_to_successFragment)
                            }
                        }
                    }
                }
                isDeleteBasket.observe(viewLifecycleOwner) { it ->
                    it?.let {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        println(it.message)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}