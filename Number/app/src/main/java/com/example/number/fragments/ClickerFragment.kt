package com.example.number.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.number.R
import com.example.number.databinding.FragmentClickerBinding
import com.example.number.viewmodels.ClickerViewModel
import com.example.number.viewmodels.states.ClickerState
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import org.koin.android.viewmodel.ext.android.viewModel


class ClickerFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentClickerBinding

    private val viewModel by viewModel<ClickerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.clickerScreen.setOnClickListener {
            viewModel.getStateForAnim()
            binding.clickerNumber.text =
                (binding.clickerNumber.text.toString().toInt() + viewModel.getBuster()).toString()
        }
        binding.submitBinaryButton.setOnClickListener {
            viewModel.saveClickerNumber(binding.clickerNumber.text.toString().toInt())
            viewModel.checkNumber()
            viewModel.updateGroups()

        }
        viewModel.state.observe(viewLifecycleOwner, {
            when (it) {
                is ClickerState.NumberNotFound -> {
                    animZeroFirst?.start()
                    animZeroSecond?.start()
                }
                is ClickerState.NumberFound -> {
                    viewModel.updateGroups()
                    animOneFirst?.start()
                    animOneSecond?.start()
                    animOneThird?.start()
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
                ClickerState.FirstClickerOpen -> {
                    showGoal(getString(R.string.clickerDescription))
                }
            }
        })

        val adView = AdView(requireContext())
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        MobileAds.initialize(requireContext()) {}
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

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
fun Fragment.showError(message: String): AlertDialog =
    AlertDialog.Builder(requireContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
        .setMessage(message)
        .setCancelable(true)
        .setPositiveButton(getString(R.string.yes), null)
        .create()

fun Fragment.showGoal(message: String){
    val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogStyle)
    builder.setMessage(message)
    builder.setNegativeButton(getString(R.string.confirm)) { dialog, _ ->
        dialog.dismiss()
    }
    val alertDialog = builder.create()
    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    Handler(Looper.getMainLooper()).postDelayed({
        alertDialog.show()
    }, 1000)
}