package com.example.number.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.number.R
import com.example.number.adapters.ShopAdapter
import com.example.number.databinding.FragmentShopBinding
import com.example.number.interfaces.ClickListener
import com.example.number.model.ShopEntity
import com.example.number.viewmodels.ShopViewModel
import com.example.number.viewmodels.states.ShopState
import org.koin.android.viewmodel.ext.android.viewModel

class ShopFragment : Fragment(), ClickListener {

    private val viewModel by viewModel<ShopViewModel>()

    private lateinit var adapter: ShopAdapter
    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false)
        binding.shopRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = ShopAdapter(arrayListOf(), this)
        binding.shopRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapter
        }
        binding.shopRecyclerView.adapter = adapter
        viewModel.state.observe(viewLifecycleOwner, {
            when (it) {
                is ShopState.PurchaseItem -> {
                    val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogStyle)
                    builder.setTitle(getString(R.string.confirmation))
                    builder.setMessage(getString(R.string.buy))

                    builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
                        viewModel.purchase()
                    }

                    builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    val alertDialog = builder.create()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    alertDialog.show()
                }
                is ShopState.NotEnoughNumbers -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.noMoneyNoHoney), Toast.LENGTH_SHORT
                    ).show()
                }
                is ShopState.EnableItem -> {
                    viewModel.enableBuster()
                }
                is ShopState.Error -> {
                    showError(resources.getString(R.string.unknownError)).show()
                }
                is ShopState.FirstShopLaunch -> {
                    val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogStyle)
                    builder.setMessage(getString(R.string.shopDescription))
                    builder.setNegativeButton(getString(R.string.confirm)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    val alertDialog = builder.create()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    alertDialog.show()
                }
                is ShopState.Idle -> {
                    adapter.addBooks(it.shopEntities as MutableList<ShopEntity>)
                }
                else -> {
                    showError(resources.getString(R.string.unknownError)).show()
                }
            }
        })


        return binding.root
    }

    override fun onCellClickListener(position: Int) {
        viewModel.isPurchased(position)
    }

}

fun Fragment.showError(message: String): AlertDialog =
    AlertDialog.Builder(requireContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
        .setMessage(message)
        .setCancelable(true)
        .setPositiveButton(getString(R.string.yes), null)
        .create()