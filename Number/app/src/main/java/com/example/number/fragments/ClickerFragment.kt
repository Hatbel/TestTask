package com.example.number.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.number.R
import com.example.number.databinding.FragmentClickerBinding
import com.example.number.viewmodels.ClickerState
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
        val animOneFirst: AnimatedVectorDrawableCompat? =
            AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.animatorvectordrawable)
        binding.animationOneImageView.setImageDrawable(animOneFirst)
        val animOneSecond: AnimatedVectorDrawableCompat? =
            AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.animatorvectordrawable)
        binding.animationOneSecondImageView.setImageDrawable(animOneSecond)
        val animOneThird: AnimatedVectorDrawableCompat? =
            AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.animatorvectordrawable)
        binding.animationOneThirdImageView.setImageDrawable(animOneThird)
        val animZeroFirst: AnimatedVectorDrawableCompat? =
            AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.animator_for_zero)
        binding.animationZeroImageView.setImageDrawable(animZeroFirst)
        val animZeroSecond: AnimatedVectorDrawableCompat? =
            AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.animator_for_zero)
        binding.animationZeroSecondImageView.setImageDrawable(animZeroSecond)
        binding.clickerScreen.setOnClickListener() {
            viewModel.getStateForAnim()
            binding.clickerNumber.text =
                (binding.clickerNumber.text.toString().toInt() + viewModel.getBuster()).toString()
        }
        binding.submitBinaryButton.setOnClickListener {
            viewModel.saveClickerNumber(binding.clickerNumber.text.toString().toInt())
            viewModel.checkNumber()


            //костыль  костыль  костыль  костыль  костыль  костыль  костыль
            viewModel.updateGroups()
            viewModel.updateGroups()
            //костыль  костыль  костыль  костыль  костыль  костыль  костыль

        }
        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ClickerState.NumberNotFound -> {
                    //Toast
                }
                is ClickerState.NumberFound -> {
                    //Toast
                }
                is ClickerState.FirstForAnim -> {
                    animOneFirst?.start()
                }
                is ClickerState.SecondForAnim -> {
                    animOneSecond?.start()
                }
                is ClickerState.ThirdForAnim -> {
                    animOneThird?.start()
                }
                is ClickerState.ForthForAnim -> {
                    animZeroFirst?.start()
                }
                is ClickerState.FifthForAnim -> {
                    animZeroSecond?.start()
                }
            }
        })

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