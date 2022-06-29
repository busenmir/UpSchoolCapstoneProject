package com.example.capstoneproject.main.carddetail

import android.annotation.SuppressLint
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.capstoneproject.R

import com.example.capstoneproject.databinding.FragmentCardDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class CardDetailFragment : BottomSheetDialogFragment() {

    private var _binding : FragmentCardDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { CardDetailViewModel(requireContext())}

    private var auth = Firebase.auth
    private val args : CardDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_card_detail,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = args.productsModel
        binding.apply {
            productsModel = model
            Picasso.get().load(model.image).into(productsImage)
        }

        binding.addCartButton.setOnClickListener {
            viewModel.addProductToBasket(auth.currentUser!!.email!! ,model.title!!,model.price!!,model.description!!,model.category!!,model.image!!,model.rate!!,model.count!!,model.sale_state!!)
            viewModel.isProAddedBasket.observe(viewLifecycleOwner){
                it?.let {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    println(it.message)
                }
            }
        }
        binding.favoriteButton.setOnClickListener {
            model.let {
                if(it != null){
                    binding.favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                    binding.favoriteButton.isSelected = true
                    viewModel.addProductsToFavorites(it)
                }else{
                    binding.favoriteButton.setImageResource(R.color.black)
                    binding.favoriteButton.isSelected = false
                }

            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
