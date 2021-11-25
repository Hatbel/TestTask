package com.example.number.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.number.modules.SessionManager
import com.example.number.modules.ThemeHelper
import com.example.number.repository.NumbersRepository
import com.example.number.viewmodels.states.TreeState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class TreeViewModel(
    private val sessionManager: SessionManager,
    private val repository: NumbersRepository,
    private var themeHelper: ThemeHelper
) : ViewModel() {

    private val _state = MutableLiveData<TreeState>()
    val state: LiveData<TreeState>
        get() = _state

    init {
        _state.postValue(TreeState.Idle)
        var isFoundAmount = 0
        viewModelScope.launch {
            val groups = repository.getBinaryNumbersGroups()
            for (group in groups) {
                if (group.isCollected) isFoundAmount += 1
            }
            if (isFoundAmount == 40) {
                _state.postValue(TreeState.LeafsAll(themeHelper.generateTheme(isFoundAmount)))
            } else {
                _state.postValue(TreeState.Leafs(themeHelper.generateTheme(isFoundAmount)))
            }
        }
    }

    fun getPlayTime() : Int{
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sessionManager.firstOpenDate)
        return (sessionManager.counterSaver + (Date().time - date.time) / (1000 * 60 * 60 * 24)).toInt()
    }

}