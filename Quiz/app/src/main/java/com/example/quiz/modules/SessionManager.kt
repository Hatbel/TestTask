package com.example.quiz.modules

import android.content.Context
import androidx.core.content.edit

const val TRUE_COUNT = "true_count"
const val QUIZ_SIZE = "quiz_size"

class SessionManager (context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    var counter: Int
        get() = prefs.getInt(TRUE_COUNT, 0)
        set(value) = prefs.edit {
            putInt(TRUE_COUNT, value)
        }
    var size: Int
        get() = prefs.getInt(QUIZ_SIZE, 0)
        set(value) = prefs.edit {
            putInt(QUIZ_SIZE, value)
        }

    fun clearPrefs() {
        prefs.edit().remove(TRUE_COUNT).apply()
        prefs.edit().remove(QUIZ_SIZE).apply()
    }
}