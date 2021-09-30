package com.example.number.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.number.R
import com.example.number.adapters.ShopAdapter
import com.example.number.databinding.FragmentShopBinding
import com.example.number.viewmodels.ShopState
import com.example.number.viewmodels.ShopViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class ShopFragment : Fragment() {

    private val viewModel by viewModel<ShopViewModel>()

    private val pagingAdapter = ShopAdapter(ShopAdapter.ShopComparator)
    private lateinit var navController: NavController
    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        navController = NavHostFragment.findNavController(this)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false)
        binding.shopRecyclerView.adapter = pagingAdapter
        binding.shopRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pagingAdapter
        }
        lifecycleScope.launch {
            viewModel.characters.collectLatest { data ->
                pagingAdapter.submitData(data)
            }
        }
        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ShopState.Shop -> {
                }
                is ShopState.Error -> {
                    showError(resources.getString(R.string.unknownError)).show()
                }
                is ShopState.Loading -> {

                }
                else -> {
                    showError(resources.getString(R.string.unknownError)).show()
                }
            }
        })


        return binding.root
    }
}

fun Fragment.showError(message: String): AlertDialog =
    AlertDialog.Builder(requireContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
        .setMessage(message)
        .setCancelable(true)
        .setPositiveButton("OK", null)
        .create()