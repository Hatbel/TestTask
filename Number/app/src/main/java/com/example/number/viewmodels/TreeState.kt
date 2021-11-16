package com.example.number.viewmodels

import android.content.res.Resources
import com.example.number.model.ShopEntity

sealed class TreeState {
    object Idle : TreeState()
    data class Leafs(val theme: Resources.Theme) : TreeState()
}