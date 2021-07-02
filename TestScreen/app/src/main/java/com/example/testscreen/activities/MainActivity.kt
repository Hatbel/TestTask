package com.example.testscreen.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import com.example.testscreen.R
import com.example.testscreen.modules.ScreenState
import com.example.testscreen.viewModels.MainViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {
    lateinit var mAdView: AdView
    private lateinit var bigNumber: TextView
    private lateinit var infoTextView: TextView
    private lateinit var shareButton: Button
    private lateinit var rateButton: Button
    private val viewModel by viewModel<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this)

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.outline_arrow_back_ios_white_24dp)
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient))
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.main_text_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bigNumber = findViewById(R.id.big_number_textview)
        val content = SpannableString(String.format("%.2f", viewModel.countBMI()))
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        bigNumber.text = content
        infoTextView = findViewById(R.id.info_textView)
        shareButton = findViewById(R.id.shareButton)
        rateButton = findViewById(R.id.rateButton)

        shareButton.setOnClickListener {
            val activityView = window.decorView.rootView
            val cachePath = File(baseContext.cacheDir, "images")
            cachePath.mkdirs()
            val stream = FileOutputStream("$cachePath/image.png")
            getScreenShot(activityView).compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()

            val imagePath = File(this@MainActivity.cacheDir, "images")
            val newFile = File(imagePath, "image.png")
            val contentUri: Uri? = FileProvider.getUriForFile(
                this@MainActivity,
                "com.example.testscreen.fileprovider",
                newFile
            )

            if (contentUri != null) {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                shareIntent.setDataAndType(contentUri, contentResolver.getType(contentUri))
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
                startActivity(Intent.createChooser(shareIntent, "Choose an app"))
            }
        }
        rateButton.setOnClickListener{
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + this.packageName)
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + this.packageName)
                    )
                )
            }
        }

        viewModel.state.observe(this, Observer {
            when (it) {
                ScreenState.Idle -> {
                }
                ScreenState.Lack -> {
                    infoTextView.text =
                        resources.getString(R.string.hello) + " " + viewModel.getName() + " " + resources.getString(
                            R.string.lack
                        ) + "\n" + resources.getString(
                            R.string.bmi
                        ) + "\n" + resources.getString(
                            R.string.panderal
                        ) + String.format("%.2f", viewModel.countPanderal())
                }
                ScreenState.Normal -> {
                    infoTextView.text =
                        resources.getString(R.string.hello) + " " + viewModel.getName() + " " + resources.getString(
                            R.string.normal
                        ) + "\n" + resources.getString(
                            R.string.bmi
                        ) + "\n" + resources.getString(
                            R.string.panderal
                        ) + String.format("%.2f", viewModel.countPanderal())
                }
                ScreenState.Overweight -> {
                    infoTextView.text =
                        resources.getString(R.string.hello) + " " + viewModel.getName() + " " + resources.getString(
                            R.string.over
                        ) + "\n" + resources.getString(
                            R.string.bmi
                        ) + "\n" + resources.getString(
                            R.string.panderal
                        ) + String.format("%.2f", viewModel.countPanderal())
                }
                ScreenState.Obese1 -> {
                    infoTextView.text =
                        resources.getString(R.string.hello) + " " + viewModel.getName() + " " + resources.getString(
                            R.string.obese1
                        ) + "\n" + resources.getString(
                            R.string.bmi
                        ) + "\n" + resources.getString(
                            R.string.panderal
                        ) + String.format("%.2f", viewModel.countPanderal())
                }
                ScreenState.Obese2 -> {
                    infoTextView.text =
                        resources.getString(R.string.hello) + " " + viewModel.getName() + " " + resources.getString(
                            R.string.obese2
                        ) + "\n" + resources.getString(
                            R.string.bmi
                        ) + "\n" + resources.getString(
                            R.string.panderal
                        ) + String.format("%.2f", viewModel.countPanderal())
                }
                ScreenState.Obese3 -> {
                    infoTextView.text =
                        resources.getString(R.string.hello) + " " + viewModel.getName() + " " + resources.getString(
                            R.string.obese3
                        ) + "\n" + resources.getString(
                            R.string.bmi
                        ) + "\n" + resources.getString(
                            R.string.panderal
                        ) + String.format("%.2f", viewModel.countPanderal())
                }
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getScreenShot(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }
}