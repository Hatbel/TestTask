package com.example.testscreen.modules

sealed class ScreenState {
    object Correct : ScreenState()
    object Idle : ScreenState()
    object Error : ScreenState()
    object Lack : ScreenState()
    object Normal : ScreenState()
    object Overweight : ScreenState()
    object Obese1 : ScreenState()
    object Obese2 : ScreenState()
    object Obese3 : ScreenState()
}