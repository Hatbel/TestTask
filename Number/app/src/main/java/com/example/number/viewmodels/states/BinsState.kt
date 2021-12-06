package com.example.number.viewmodels.states

sealed class BinsState {
    object FirstBinsOpen : BinsState()
    object Idle : BinsState()
}