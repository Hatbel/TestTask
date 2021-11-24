package com.example.number.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.number.model.BinaryNumberDB
import com.example.number.model.BinaryNumberGroup
import com.example.number.modules.SessionManager
import com.example.number.repository.NumbersRepository
import com.example.number.viewmodels.states.ClickerState
import kotlinx.coroutines.launch
import kotlin.random.Random

class ClickerViewModel(
    private val sessionManager: SessionManager,
    private val repository: NumbersRepository
) : ViewModel() {

    private val _state = MutableLiveData<ClickerState>()
    val state: LiveData<ClickerState>
        get() = _state

    fun getSavedNumber(): Int {
        return sessionManager.counterSaver

    }

    fun saveClickerNumber(number: Int) {
        sessionManager.counterSaver = number
    }

    fun getBuster() = sessionManager.buster

    fun getStateForAnim(){
        when (Random.nextInt(0, 5)) {
            0 -> _state.postValue(ClickerState.FirstForAnim)
            1 -> _state.postValue(ClickerState.SecondForAnim)
            2 -> _state.postValue(ClickerState.ThirdForAnim)
            3 -> _state.postValue(ClickerState.ForthForAnim)
            4 -> _state.postValue(ClickerState.FifthForAnim)
        }

    }

    fun updateGroups() {
        viewModelScope.launch {
            var group: BinaryNumberGroup
            for (groupId in 1..40) {
                var isFoundAmount = 0
                val numbersInGroup = repository.getNumbersByGroupId(groupId)
                group = repository.getGroupById(groupId)
                for (number: BinaryNumberDB in numbersInGroup)
                    if (number.isFound) isFoundAmount += 1
                if (isFoundAmount == 25) {
                    group.isCollected = true
                }
                group.collectedAmount = isFoundAmount
                repository.updateGroupId(group)
            }
        }
    }

    fun checkNumber() {
        viewModelScope.launch {
            try {
                val binNumber: BinaryNumberDB =
                    repository.getNumberByNumber(sessionManager.counterSaver.toString())
                if (!binNumber.isFound) {
                    binNumber.isFound = true
                    repository.updateBinaryNumber(binNumber)
                    _state.postValue(ClickerState.NumberFound)
                }
            } catch (e: Exception) {
                _state.postValue(ClickerState.NumberNotFound)
            }
        }
    }
}