package com.example.quiz.beans

import com.google.gson.annotations.Expose

data class Quizes (
    @Expose(serialize = false, deserialize = true)
    var questions : List<Quiz>
)

data class Quiz (
    @Expose(serialize = false, deserialize = true)
    var questionText: String,
    var answers : List<QuizData>,
    var correctAnswerIndex : Int
)

data class QuizData(
        @Expose(serialize = false, deserialize = true)
        var answerText: String
)