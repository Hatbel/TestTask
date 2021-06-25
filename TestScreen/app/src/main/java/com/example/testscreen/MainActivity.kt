package com.example.testscreen

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var mAdView : AdView
    private lateinit var bigNumber : TextView
    private lateinit var infoTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.outline_arrow_back_ios_white_24dp)
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient))
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        supportActionBar?.setCustomView(R.layout.text_layout);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bigNumber = findViewById(R.id.big_number_textview)
        val content = SpannableString(String.format("%.2f", Random.nextDouble(0.00, 100.00)))
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        bigNumber.text = content
        infoTextView = findViewById(R.id.info_textView)
        infoTextView.text = resources.getString(R.string.hello) + "\n" + resources.getString(R.string.bmi) + "\n" + resources.getString(R.string.panderal) + String.format("%.2f", Random.nextDouble(0.00, 100.00))

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}