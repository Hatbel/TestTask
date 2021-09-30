package com.example.number.modules

import androidx.room.Room
import com.example.number.database.LocalDataBase
import com.example.number.repository.NumbersRepository
import com.example.number.repository.ShopPagingDataSource
import com.example.number.viewmodels.ClickerViewModel
import com.example.number.viewmodels.ShopViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataBaseModule = module {
    single { get<LocalDataBase>().binaryNumberDao() }
    single { get<LocalDataBase>().shopDao() }
    single {
        Room.databaseBuilder(androidContext(), LocalDataBase::class.java, "LocalDataBase")
            .createFromAsset("database/binaryNumber.db")
            .build()
    }
}
val appModule = module {
    single { NumbersRepository(get(), get(), get()) }
    single { SessionManager(androidContext()) }
    single { ShopPagingDataSource(get()) }
}

val viewModelsModule = module {
    viewModel { ClickerViewModel(get()) }
    viewModel { ShopViewModel(get(), get(), get()) }
}