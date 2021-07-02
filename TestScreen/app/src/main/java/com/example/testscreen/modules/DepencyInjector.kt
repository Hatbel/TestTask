package com.example.testscreen.modules

import com.example.testscreen.viewModels.MainViewModel
import com.example.testscreen.viewModels.StartViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel


val appModule = module {
        single { SessionManager(androidContext()) }
        viewModel { StartViewModel(get()) }
        viewModel { MainViewModel(get()) }
}
