package com.example.number.modules

import android.content.Context
import androidx.core.content.edit

const val COUNTER_SAVER = "counter_saver"
const val BUSTER_COUNTER = "buster_counter"
const val BUSTER = "buster"
const val GROUP_ID = "group_id"

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    var counterSaver: Int
        get() = prefs.getInt(COUNTER_SAVER, 1)
        set(value) = prefs.edit {
            putInt(COUNTER_SAVER, value)
        }

    var groupId: Int
        get() = prefs.getInt(GROUP_ID, 1)
        set(value) = prefs.edit {
            putInt(GROUP_ID, value)
        }

    var busterCounter: Int
        get() = prefs.getInt(BUSTER_COUNTER, 1)
        set(value) = prefs.edit {
            putInt(BUSTER_COUNTER, value)
        }

    var buster: Int
        get() = prefs.getInt(BUSTER, 1)
        set(value) = prefs.edit {
            putInt(BUSTER, value)
        }

    fun clearPrefs() {
        prefs.edit().remove(COUNTER_SAVER).apply()
        prefs.edit().remove(BUSTER_COUNTER).apply()
        prefs.edit().remove(BUSTER).apply()
        prefs.edit().remove(GROUP_ID).apply()
    }
}