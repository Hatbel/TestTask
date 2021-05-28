package com.example.quiz.modules

import com.example.quiz.repositories.Repository
import com.example.quiz.viewModels.QuizViewModel
import com.example.quiz.viewModels.ResultViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val appModule = module {
    single { Helper(androidContext()) }
    single { SessionManager(androidContext()) }
    single { Repository(get(),get()) }
    viewModel { QuizViewModel(get(),get()) }
    viewModel { ResultViewModel(get(),get()) }
}