package com.example.number.modules

import android.content.Context
import androidx.core.content.edit

const val COUNTER_SAVER = "counter_saver"
const val BUSTER = "buster"
const val GROUP_ID = "group_id"
const val DATE_OF_CLOSE = "date_of_close"
const val FIRST_OPEN_DATE = "first_open_date"
const val IS_FIRST_OPEN = "is_first_open"
const val IS_CLICKER_FIRST_OPEN = "is_clicker_first_open"
const val IS_SHOP_FIRST_OPEN = "is_shop_first_open"
const val IS_BINS_FIRST_OPEN = "is_bins_first_open"
const val IS_TREE_FIRST_OPEN = "is_tree_first_open"
const val IS_APP_OPENED = "is_app_opened"

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

    var isAppOpened: Boolean
        get() = prefs.getBoolean(IS_APP_OPENED, true)
        set(value) = prefs.edit {
            putBoolean(IS_APP_OPENED, value)
        }

    var isClickerFirstOpen: Boolean
        get() = prefs.getBoolean(IS_CLICKER_FIRST_OPEN, true)
        set(value) = prefs.edit {
            putBoolean(IS_CLICKER_FIRST_OPEN, value)
        }

    var isShopFirstOpen: Boolean
        get() = prefs.getBoolean(IS_SHOP_FIRST_OPEN, true)
        set(value) = prefs.edit {
            putBoolean(IS_SHOP_FIRST_OPEN, value)
        }

    var isBinsFirstOpen: Boolean
        get() = prefs.getBoolean(IS_BINS_FIRST_OPEN, true)
        set(value) = prefs.edit {
            putBoolean(IS_BINS_FIRST_OPEN, value)
        }

    var isTreeFirstOpen: Boolean
        get() = prefs.getBoolean(IS_TREE_FIRST_OPEN, true)
        set(value) = prefs.edit {
            putBoolean(IS_TREE_FIRST_OPEN, value)
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
}