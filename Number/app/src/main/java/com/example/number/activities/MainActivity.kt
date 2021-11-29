package com.example.number.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.number.R
import com.example.number.viewmodels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView
                >(R.id.bottomNavigationView)
        val navController = findNavController(R.id.navFragment)
        bottomNavigationView.setupWithNavController(
            navController
        )
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.slide_in_left)
            .setExitAnim(R.anim.slide_in_right)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_in_right)
            .setPopUpTo(navController.graph.startDestination, false)
            .build()
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.treeFragment -> {
                    navController.navigate(R.id.treeFragment, null, options)
                }
                R.id.shopFragment -> {
                    navController.navigate(R.id.shopFragment, null, options)
                }
                R.id.binaryNumbersFragment -> {
                    navController.navigate(R.id.binaryNumbersFragment, null, options)
                }
                R.id.clickerFragment -> {
                    navController.navigate(R.id.clickerFragment, null, options)
                }
                R.id.groupNumbersFragment -> {
                    navController.navigate(R.id.groupNumbersFragment, null, options)
                }
            }
            true
        }
        viewModel.firstOpen()
        viewModel.addToClicker()

    }

    override fun onStop() {
        viewModel.saveCloseDate()
        Log.e("no datasdasdasdasdasdsae yet ", "date = null")
        super.onStop()
    }
}
