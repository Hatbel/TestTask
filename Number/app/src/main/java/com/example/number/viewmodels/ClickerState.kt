package com.example.number.viewmodels

sealed class ClickerState {
    object NumberFound : ClickerState()
    object NumberNotFound : ClickerState()
}