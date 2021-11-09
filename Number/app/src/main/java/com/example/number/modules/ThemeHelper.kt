package com.example.number.modules

import android.content.Context
import android.content.res.Resources
import com.example.number.R

class ThemeHelper(private val context: Context) {
    fun generateTheme(themeNumber: Int): Resources.Theme {
        val theme = context.resources.newTheme()
        when (themeNumber) {
            1 -> {
                theme.applyStyle(R.style.black_leaf1, false)
                return theme
            }
            2 -> {
                theme.applyStyle(R.style.black_leaf2, false)
                return theme
            }
            3 -> {
                theme.applyStyle(R.style.black_leaf3, false)
                return theme
            }
            4 -> {
                theme.applyStyle(R.style.black_leaf4, false)
                return theme
            }
            5 -> {
                theme.applyStyle(R.style.black_leaf5, false)
                return theme
            }
            6 -> {
                theme.applyStyle(R.style.black_leaf6, false)
                return theme
            }
            7 -> {
                theme.applyStyle(R.style.black_leaf7, false)
                return theme
            }
            8 -> {
                theme.applyStyle(R.style.black_leaf8, false)
                return theme
            }
            9 -> {
                theme.applyStyle(R.style.black_leaf9, false)
                return theme
            }
            10 -> {
                theme.applyStyle(R.style.black_leaf10, false)
                return theme
            }
            11 -> {
                theme.applyStyle(R.style.black_leaf11, false)
                return theme
            }
            12 -> {
                theme.applyStyle(R.style.black_leaf12, false)
                return theme
            }
            13 -> {
                theme.applyStyle(R.style.black_leaf13, false)
                return theme
            }
            14 -> {
                theme.applyStyle(R.style.black_leaf14, false)
                return theme
            }
            15 -> {
                theme.applyStyle(R.style.black_leaf15, false)
                return theme
            }
            16 -> {
                theme.applyStyle(R.style.black_leaf16, false)
                return theme
            }
            17 -> {
                theme.applyStyle(R.style.black_leaf17, false)
                return theme
            }
            18 -> {
                theme.applyStyle(R.style.black_leaf18, false)
                return theme
            }
            19 -> {
                theme.applyStyle(R.style.black_leaf19, false)
                return theme
            }
            20 -> {
                theme.applyStyle(R.style.black_leaf20, false)
                return theme
            }
            21 -> {
                theme.applyStyle(R.style.black_leaf21, false)
                return theme
            }
            22 -> {
                theme.applyStyle(R.style.black_leaf22, false)
                return theme
            }
            23 -> {
                theme.applyStyle(R.style.black_leaf23, false)
                return theme
            }
            24 -> {
                theme.applyStyle(R.style.black_leaf24, false)
                return theme
            }
            25 -> {
                theme.applyStyle(R.style.black_leaf25, false)
                return theme
            }
            26 -> {
                theme.applyStyle(R.style.black_leaf26, false)
                return theme
            }
            27 -> {
                theme.applyStyle(R.style.black_leaf27, false)
                return theme
            }
            28 -> {
                theme.applyStyle(R.style.black_leaf28, false)
                return theme
            }
            29 -> {
                theme.applyStyle(R.style.black_leaf29, false)
                return theme
            }
            30 -> {
                theme.applyStyle(R.style.black_leaf30, false)
                return theme
            }
            31 -> {
                theme.applyStyle(R.style.black_leaf31, false)
                return theme
            }
            32 -> {
                theme.applyStyle(R.style.black_leaf32, false)
                return theme
            }
            33 -> {
                theme.applyStyle(R.style.black_leaf33, false)
                return theme
            }
            34 -> {
                theme.applyStyle(R.style.black_leaf34, false)
                return theme
            }
            35 -> {
                theme.applyStyle(R.style.black_leaf35, false)
                return theme
            }
            36 -> {
                theme.applyStyle(R.style.black_leaf36, false)
                return theme
            }
            37 -> {
                theme.applyStyle(R.style.black_leaf37, false)
                return theme
            }
            38 -> {
                theme.applyStyle(R.style.black_leaf38, false)
                return theme
            }
            39 -> {
                theme.applyStyle(R.style.black_leaf39, false)
                return theme
            }
            40 -> {
                theme.applyStyle(R.style.black_leaf40, false)
                return theme
            }
            else -> {
                theme.applyStyle(R.style.default_leaf, false)
                return theme
            }
        }
    }
}