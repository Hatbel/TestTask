package com.example.number.repository

import androidx.paging.*
import com.example.number.database.BinaryNumberDao
import com.example.number.database.ShopDao
import com.example.number.model.ShopEntity
import com.example.number.modules.SessionManager
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class NumbersRepository(
    private val sessionManager: SessionManager,
    private val shopDao: ShopDao,
    private val binaryNumberDao: BinaryNumberDao
){
}