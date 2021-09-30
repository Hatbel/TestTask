package com.example.number.modules

import android.content.Context
import androidx.core.content.edit

const val COUNTER_SAVER = "counter_saver"

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    var counterSaver: Int
        get() = prefs.getInt(COUNTER_SAVER, 1)
        set(value) = prefs.edit {
            putInt(COUNTER_SAVER, value)
        }

    fun clearPrefs() {
        prefs.edit().remove(COUNTER_SAVER).apply()
    }
}