package com.example.number.viewmodels

sealed class ClickerState {
    object NumberFound : ClickerState()
    object NumberNotFound : ClickerState()
    object FirstForAnim : ClickerState()
    object SecondForAnim : ClickerState()
    object ThirdForAnim : ClickerState()
    object ForthForAnim : ClickerState()
    object FifthForAnim : ClickerState()
}