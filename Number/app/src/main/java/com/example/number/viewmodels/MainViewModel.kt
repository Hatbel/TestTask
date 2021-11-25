package com.example.number.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.number.modules.SessionManager
import com.example.number.repository.NumbersRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(
    private val sessionManager: SessionManager,
    private val repository: NumbersRepository
) : ViewModel() {
    fun firstOpen() {
        if (sessionManager.isFirstOpen) {
            sessionManager.firstOpenDate =
                (SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date())).toString()
            sessionManager.isFirstOpen = false
        }
    }

    fun saveCloseDate() {
        sessionManager.dateOfClose =
            (SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date())).toString()
    }

    fun addToClicker() {

            var isFoundAmount = 0
            viewModelScope.launch {
                val groups = repository.getBinaryNumbersGroups()
                for (group in groups) {
                    if (group.isCollected) isFoundAmount += 1
                }
                try {
                val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sessionManager.dateOfClose)
                sessionManager.counterSaver =
                    (sessionManager.counterSaver + (Date().time - date.time) / (1000 * 60) * isFoundAmount).toInt()
                } catch (e: Exception) {
                    Log.e("no date yet ", "date = null")
                }
            }

    }
}