package com.example.number.viewmodels.states

import android.content.res.Resources

sealed class TreeState {
    object Idle : TreeState()
    data class Leafs(val theme: Resources.Theme) : TreeState()
}