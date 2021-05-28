package com.example.quiz.repositories

import com.example.quiz.R
import com.example.quiz.beans.Quizes
import com.example.quiz.modules.Helper
import com.example.quiz.modules.SessionManager
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader


class Repository(private val helper: Helper, private val sessionManager: SessionManager) {
    fun getQuestions(): Quizes {
        val br = BufferedReader(InputStreamReader(helper.getFileFromRes(R.raw.quiz)))
        return Gson().fromJson(br, Quizes::class.java)
    }
    fun clear(){
        sessionManager.clearPrefs()
    }
}
