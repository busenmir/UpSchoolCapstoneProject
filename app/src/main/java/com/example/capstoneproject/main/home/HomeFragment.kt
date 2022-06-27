package com.example.capstoneproject.main.home

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val productsAdapter by lazy { ProductsAdapter() }

    private val discountedAdapter by lazy { DiscountedAdapter() }

    private val categoryAdapter by lazy {CategoryAdapter() }

    private val viewModel by lazy { HomeFragmentViewModel(requireContext()) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        categoryAdapter.onCategoryItemClick = {
            viewModel.getProductsbyCategory(it)
        }
    }
    private fun initObservers() {
        with(binding){
            with(viewModel){
                isLoading.observe(viewLifecycleOwner){
                    if(!it) LoadingView.visibility = View.GONE
                }
                _discountedList.observe(viewLifecycleOwner){ list ->
                    discountedRecyclerView.apply {
                        setHasFixedSize(true)
                        adapter = discountedAdapter.also {
                            it.updateProductsList(list)
                        }
                    }
                }
                _productsList.observe(viewLifecycleOwner){ list ->
                    productsRecyclerView.apply {
                        setHasFixedSize(true)
                        adapter = productsAdapter.also {
                            it.updateProductsList(list)
                        }
                    }
                }
                _categoryList.observe(viewLifecycleOwner){list ->
                    categoryRecyclerView.apply{
                        setHasFixedSize(true)
                        adapter=categoryAdapter.also {
                            it.updateProductsList(list)
                        }
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