package com.example.number.viewmodels

import androidx.lifecycle.ViewModel
import com.example.number.modules.SessionManager
import com.example.number.repository.NumbersRepository
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
        sessionManager.isAppOpened = false
    }
}