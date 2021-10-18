package com.example.number

import ZoomOutPageTransformer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.number.databinding.ActivityMainBinding
import com.example.number.fragments.BinaryNumbersFragment
import com.example.number.fragments.ClickerFragment
import com.example.number.fragments.ShopFragment
import com.example.number.fragments.TreeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val NUM_PAGES = 4

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView
                >(R.id.bottomNavigationView)
        val navController = findNavController(R.id.navFragment)
        bottomNavigationView.setupWithNavController(
            navController
        )
    }

}
