package com.example.capstoneproject.main.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.FragmentFavoriteBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {FavoriteViewModel(requireContext())}
    private val favoriteAdapter by lazy { FavoriteAdapter() }

    private var auth = Firebase.auth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorite,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        with(binding){
            FavoriteRecycleView.setHasFixedSize(true)
            favoriteAdapter.onItemClick = {
                viewModel.deleteFavoritesFromBasket(it)
            }
        }
    }
    private fun initObservers(){
        with(binding){
            with(viewModel){
                _favoritesBasket.observe(viewLifecycleOwner){list ->
                    FavoriteRecycleView.apply {
                        setHasFixedSize(true)
                        adapter = favoriteAdapter.also {
                            it.updateBasketList(list)
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