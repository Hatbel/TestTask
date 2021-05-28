package com.example.quiz.modules

import android.content.Context
import androidx.annotation.StringRes

class Helper(private val context: Context) {
    fun getFileFromRes(fileId: Int) = context.resources.openRawResource(fileId)
    fun getStringFromRes(@StringRes stringId: Int) = context.resources.getString(stringId)
}