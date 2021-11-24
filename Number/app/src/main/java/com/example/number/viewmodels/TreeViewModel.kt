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

class TreeViewModel(
    private val sessionManager: SessionManager,
    private val repository: NumbersRepository,
    private var themeHelper: ThemeHelper
) : ViewModel() {

    private val _state = MutableLiveData<TreeState>()
    val state: LiveData<TreeState>
        get() = _state
    init{
        _state.postValue(TreeState.Idle)
        var isFoundAmount = 0
        viewModelScope.launch {
            val groups = repository.getBinaryNumbersGroups()
            for (group in groups) {
                if (group.isCollected) isFoundAmount += 1
            }
            _state.postValue(TreeState.Leafs(themeHelper.generateTheme(isFoundAmount)))
        }
    }

}