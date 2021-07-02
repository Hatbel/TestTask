package com.example.testscreen.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.testscreen.R
import com.example.testscreen.modules.ScreenState
import com.example.testscreen.viewModels.StartViewModel
import com.google.android.gms.ads.*
import com.google.android.gms.ads.InterstitialAd
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.viewmodel.ext.android.viewModel


class StartActivity : AppCompatActivity() {

    private lateinit var weightPicker: NumberPicker
    private lateinit var heightPicker: NumberPicker
    private lateinit var genderPicker: NumberPicker
    private lateinit var calculateButton: Button
    private lateinit var name: TextInputEditText
    private val viewModel by viewModel<StartViewModel>()
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        MobileAds.initialize(this@StartActivity)
        mInterstitialAd = newInterstitialAd()

        weightPicker = findViewById(R.id.weightPicker)
        heightPicker = findViewById(R.id.heightPicker)
        genderPicker = findViewById(R.id.genderPicker)
        calculateButton = findViewById(R.id.calculateButton)
        name = findViewById(R.id.nameEditInputLayuout)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.outline_arrow_back_ios_white_24dp)
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient))
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.start_text_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        weightPicker.maxValue = 500
        weightPicker.minValue = 1
        heightPicker.maxValue = 300
        heightPicker.minValue = 1
        genderPicker.maxValue = 1
        genderPicker.minValue = 0
        val stringsToPick = arrayOf("male", "female")
        genderPicker.displayedValues = stringsToPick

        calculateButton.setOnClickListener {
            viewModel.saveResult(
                weightPicker.value,
                heightPicker.value,
                genderPicker.value,
                name.text.toString()
            )
        }
        viewModel.state.observe(this, Observer {
            when (it) {
                ScreenState.Correct -> {
                    loadInterstitial()
                    showInterstitial()
                    startActivity(Intent(this, MainActivity::class.java))
                }
                ScreenState.Idle -> {
                }
                ScreenState.Error -> name.error
            }
        })
        //throw RuntimeException("Test Crash") // Force a crash
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun newInterstitialAd(): InterstitialAd {
        val interstitialAd = InterstitialAd(this@StartActivity)
        interstitialAd.adUnitId = getString(R.string.banner_ad_unit_id)
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Toast.makeText(applicationContext, "Ad Loaded", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                Toast.makeText(applicationContext, errorCode.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onAdClosed() {
                Toast.makeText(applicationContext, "Ad Closed", Toast.LENGTH_SHORT).show()
                tryToLoadAdOnceAgain()
            }
        }
        return interstitialAd
    }

    private fun loadInterstitial() {
        val adRequest = AdRequest.Builder().build()
        mInterstitialAd?.loadAd(adRequest)
    }

    private fun showInterstitial() {
        if (mInterstitialAd != null && mInterstitialAd!!.isLoaded) {
            mInterstitialAd!!.show()
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show()
            tryToLoadAdOnceAgain()
        }
    }

    private fun tryToLoadAdOnceAgain() {
        mInterstitialAd = newInterstitialAd()
        loadInterstitial()
    }
}