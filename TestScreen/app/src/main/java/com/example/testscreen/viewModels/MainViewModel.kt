package com.example.testscreen.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testscreen.modules.ScreenState
import com.example.testscreen.modules.SessionManager

class MainViewModel(private val sessionManager: SessionManager) : ViewModel() {
    private val _state = MutableLiveData<ScreenState>()
    val state: LiveData<ScreenState>
        get() = _state

    init {
        _state.postValue(ScreenState.Idle)
        val bmi = countBMI()
        when {
            bmi < 18.5 -> {
                _state.postValue(ScreenState.Lack)
            }
            bmi in 18.5..25.0 -> {
                _state.postValue(ScreenState.Normal)
            }
            bmi in 26.0..30.0 -> {
                _state.postValue(ScreenState.Overweight)
            }
            bmi in 30.0..35.0 -> {
                _state.postValue(ScreenState.Obese1)
            }
            bmi in 35.0..40.0 -> {
                _state.postValue(ScreenState.Obese2)
            }
            bmi > 40 -> {
                _state.postValue(ScreenState.Obese3)
            }
        }
    }

    fun countBMI(): Double {
        val weight = sessionManager.weight.toDouble()
        val height = sessionManager.height.toDouble()
        return weight / (height/100 * height/100)
    }

    fun countPanderal(): Double {
        val weight = sessionManager.weight.toDouble()
        val height = sessionManager.height.toDouble()
        return weight / ((height * height * height) / 1000000)
    }
    fun getName() = sessionManager.name

}