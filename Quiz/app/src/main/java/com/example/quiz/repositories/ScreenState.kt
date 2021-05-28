package com.example.quiz.repositories

sealed class ScreenState {
    data class FirstButton(val quiz: com.example.quiz.beans.Quiz) : ScreenState()
    data class SecondButton(val quiz: com.example.quiz.beans.Quiz) : ScreenState()
    data class ThirdButton(val quiz: com.example.quiz.beans.Quiz) : ScreenState()
    data class FourthButton(val quiz: com.example.quiz.beans.Quiz) : ScreenState()
    data class NextQuestion(val quiz: com.example.quiz.beans.Quiz) : ScreenState()
    object Finish : ScreenState()
    object Idle : ScreenState()
    object Error : ScreenState()
}