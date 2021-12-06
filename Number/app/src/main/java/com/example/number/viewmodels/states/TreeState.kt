package com.example.number.viewmodels.states

import android.content.res.Resources

sealed class TreeState {
    object TreeFirstOpen : TreeState()
    data class Leafs(val theme: Resources.Theme) : TreeState()
    data class LeafsAll(val theme: Resources.Theme) : TreeState()
}