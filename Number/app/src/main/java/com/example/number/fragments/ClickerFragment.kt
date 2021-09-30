package com.example.number.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.number.R
import com.example.number.databinding.FragmentClickerBinding
import com.example.number.viewmodels.ClickerViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ClickerFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentClickerBinding

    private val viewModel by viewModel<ClickerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = NavHostFragment.findNavController(this)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_clicker, container, false)
        binding.clickerNumber.text = viewModel.getSavedNumber().toString()
        binding.clickerScreen.setOnClickListener(){
            binding.clickerNumber.text = (binding.clickerNumber.text.toString().toInt() + 1/*make plus 1 if no item purchased when I make a shop*/).toString()
        }

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveClickerNumber(binding.clickerNumber.text.toString().toInt())
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveClickerNumber(binding.clickerNumber.text.toString().toInt())
    }

    override fun onResume() {
        super.onResume()
        binding.clickerNumber.text = viewModel.getSavedNumber().toString()
    }


}