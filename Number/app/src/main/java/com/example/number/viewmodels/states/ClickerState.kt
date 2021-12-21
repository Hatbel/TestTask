package com.example.number.viewmodels.states

sealed class ClickerState {
    object NumberFound : ClickerState()
    object NumberNotFound : ClickerState()
    object FirstForAnim : ClickerState()
    object SecondForAnim : ClickerState()
    object ThirdForAnim : ClickerState()
    object ForthForAnim : ClickerState()
    object FifthForAnim : ClickerState()
    object FirstClickerOpen : ClickerState()
    data class Addition(val addition: Int) : ClickerState()
}