package com.example.quiz.viewModels

import androidx.lifecycle.ViewModel
import com.example.quiz.modules.SessionManager
import com.example.quiz.repositories.Repository

class ResultViewModel(private val repository: Repository, private val sessionManager: SessionManager) : ViewModel() {
    val result = sessionManager.counter.toString() + "/" + sessionManager.size + "  percent " + (sessionManager.counter.toDouble()/sessionManager.size.toDouble() * 100) + "%"
    fun clear(){
        repository.clear()
    }
}