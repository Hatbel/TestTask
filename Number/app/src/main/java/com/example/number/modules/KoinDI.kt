package com.example.number.modules

import androidx.room.Room
import com.example.number.database.LocalDataBase
import com.example.number.repository.BinaryGroupDataSource
import com.example.number.repository.BinaryNumbersPagingDataSource
import com.example.number.repository.NumbersRepository
import com.example.number.viewmodels.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataBaseModule = module {
    single { get<LocalDataBase>().binaryNumberDao() }
    single { get<LocalDataBase>().shopDao() }
    single { get<LocalDataBase>().binaryGroupDao() }
    single {
        Room.databaseBuilder(androidContext(), LocalDataBase::class.java, "LocalDataBase")
            .createFromAsset("database/binaryNumber.db")
            .build()
    }
}
val appModule = module {
    single { NumbersRepository(get(), get(), get()) }
    single { SessionManager(androidContext()) }
    single { BinaryGroupDataSource(get()) }
    single { BinaryNumbersPagingDataSource(get(), get()) }
    single { ThemeHelper(androidContext()) }
}

val viewModelsModule = module {
    viewModel { ClickerViewModel(get(), get()) }
    viewModel { ShopViewModel(get(), get()) }
    viewModel { BinaryNumbersViewModel(get(), get()) }
    viewModel { GroupNumbersViewModel(get()) }
    viewModel { TreeViewModel(get(), get(), get()) }
    viewModel { MainViewModel(get(), get()) }
}