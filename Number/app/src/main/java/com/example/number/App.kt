package com.example.number

import android.app.Application
import com.example.number.modules.SessionManager
import com.example.number.modules.appModule
import com.example.number.modules.dataBaseModule
import com.example.number.modules.viewModelsModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.text.SimpleDateFormat
import java.util.*

class App : Application() {

    private val sessionManager: SessionManager by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    dataBaseModule, appModule, viewModelsModule
                )
            )
        }
    }

}