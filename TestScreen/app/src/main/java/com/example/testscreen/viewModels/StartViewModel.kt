package com.example.testscreen.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testscreen.modules.ScreenState
import com.example.testscreen.modules.SessionManager

class StartViewModel(private val sessionManager: SessionManager) : ViewModel() {
    private val _state = MutableLiveData<ScreenState>()
    val state: LiveData<ScreenState>
        get() = _state

    init {
        _state.postValue(ScreenState.Idle)
        sessionManager.clearPrefs()
    }
    fun saveResult(weight: Int, height: Int, gender: Int, name: String) {
        if(name.isNullOrBlank()){
            _state.postValue(ScreenState.Error)
        }
        sessionManager.height = height
        sessionManager.weight = weight
        sessionManager.gender = gender
        sessionManager.name = name
        _state.postValue(ScreenState.Correct)
    }
}