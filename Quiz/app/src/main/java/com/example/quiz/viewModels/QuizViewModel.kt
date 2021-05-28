package com.example.quiz.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz.beans.Quiz
import com.example.quiz.beans.Quizes
import com.example.quiz.modules.SessionManager
import com.example.quiz.repositories.Repository
import com.example.quiz.repositories.ScreenState
import com.google.gson.Gson

class QuizViewModel(repository: Repository, private val sessionManager: SessionManager) : ViewModel() {
    private var quizes = mutableListOf<Quiz>()
    private var count: Int = 0
    private lateinit var question: Quiz
    private val _state = MutableLiveData<ScreenState>()
    val state: LiveData<ScreenState>
        get() = _state

    init {
        _state.postValue(ScreenState.Idle)
        repository.clear()
        try {
            quizes.addAll(repository.getQuestions().questions)
        } catch (e : Exception) {
            _state.postValue(ScreenState.Error)
        }
        sessionManager.size = quizes.size
        loadQuiz()
        trueButton()
    }

    fun countTrue(){
        sessionManager.counter+=1
    }
    fun loadQuiz() {
        if (count < quizes.size) {
            question = quizes[count]
            count++
            _state.postValue(ScreenState.NextQuestion(question))
        } else{
            _state.postValue(ScreenState.Finish)
        }
    }

    fun trueButton() {
        if (question.correctAnswerIndex == 0) _state.postValue(ScreenState.FirstButton(question))
        if (question.correctAnswerIndex == 1) _state.postValue(ScreenState.SecondButton(question))
        if (question.correctAnswerIndex == 2) _state.postValue(ScreenState.ThirdButton(question))
        if (question.correctAnswerIndex == 3) _state.postValue(ScreenState.FourthButton(question))
    }
}