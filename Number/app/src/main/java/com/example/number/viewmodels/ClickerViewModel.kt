package com.example.number.viewmodels

import androidx.lifecycle.ViewModel
import com.example.number.modules.SessionManager

class ClickerViewModel(private val sessionManager: SessionManager) : ViewModel() {

    fun getSavedNumber() : Int{
        return if(!sessionManager.counterSaver.equals(null)){
            sessionManager.counterSaver
        } else 1
    }
    fun saveClickerNumber(number : Int){
        sessionManager.counterSaver = number
    }
    fun getBuster() = sessionManager.buster
}