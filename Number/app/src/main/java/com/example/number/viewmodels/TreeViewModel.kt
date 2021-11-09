package com.example.number.viewmodels

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.number.modules.SessionManager
import com.example.number.modules.ThemeHelper
import com.example.number.repository.NumbersRepository
import kotlinx.coroutines.launch

class TreeViewModel(
    private val sessionManager: SessionManager,
    private val repository: NumbersRepository,
    private var themeHelper: ThemeHelper
) : ViewModel() {
    fun getTheme(): Resources.Theme {
        var isFoundAmount = 0
        viewModelScope.launch {
            val groups = repository.getBinaryNumbersGroups()
            for (group in groups) {
                if (group.isCollected) isFoundAmount += 1
            }
        }
        return themeHelper.generateTheme(isFoundAmount)
    }
}