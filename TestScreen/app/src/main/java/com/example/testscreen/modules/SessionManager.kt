package com.example.testscreen.modules

import android.content.Context
import androidx.core.content.edit

const val WEIGHT = "weight"
const val HEIGHT = "height"
const val GENDER = "gender"
const val NAME = "name"

class SessionManager (context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    var weight: Int
        get() = prefs.getInt(WEIGHT, 0)
        set(value) = prefs.edit {
            putInt(WEIGHT, value)
        }
    var height: Int
        get() = prefs.getInt(HEIGHT, 0)
        set(value) = prefs.edit {
            putInt(HEIGHT, value)
        }
    var gender: Int
        get() = prefs.getInt(GENDER, 0)
        set(value) = prefs.edit {
            putInt(GENDER, value)
        }
    var name: String
        get() = prefs.getString(NAME, "") ?: ""
        set(value) = prefs.edit {
            putString(NAME, value)
        }
    fun clearPrefs() {
        prefs.edit().remove(WEIGHT).apply()
        prefs.edit().remove(HEIGHT).apply()
        prefs.edit().remove(GENDER).apply()
        prefs.edit().remove(NAME).apply()
    }
}