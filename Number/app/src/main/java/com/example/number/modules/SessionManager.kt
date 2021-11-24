package com.example.number.modules

import android.content.Context
import androidx.core.content.edit

const val COUNTER_SAVER = "counter_saver"
const val BUSTER = "buster"
const val GROUP_ID = "group_id"
const val DATE_OF_CLOSE = "date_of_close"
const val FIRST_OPEN_DATE = "first_open_date"
const val IS_FIRST_OPEN = "is_first_open"

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    var dateOfClose: String
        get() = prefs.getString(DATE_OF_CLOSE, "").toString()
        set(value) = prefs.edit {
            putString(DATE_OF_CLOSE, value)
        }

    var firstOpenDate: String
        get() = prefs.getString(FIRST_OPEN_DATE, "").toString()
        set(value) = prefs.edit {
            putString(FIRST_OPEN_DATE, value)
        }

    var isFirstOpen: Boolean
        get() = prefs.getBoolean(IS_FIRST_OPEN, true)
        set(value) = prefs.edit {
            putBoolean(IS_FIRST_OPEN, value)
        }

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

    var buster: Int
        get() = prefs.getInt(BUSTER, 1)
        set(value) = prefs.edit {
            putInt(BUSTER, value)
        }

    fun clearPrefs() {
        prefs.edit().remove(COUNTER_SAVER).apply()
        prefs.edit().remove(BUSTER).apply()
        prefs.edit().remove(GROUP_ID).apply()
        prefs.edit().remove(DATE_OF_CLOSE).apply()
        prefs.edit().remove(FIRST_OPEN_DATE).apply()
        prefs.edit().remove(IS_FIRST_OPEN).apply()
    }
}