package com.example.number.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.example.number.model.ShopEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopDao {
    @Query("SELECT * FROM ShopEntity LIMIT :size")
    suspend fun getShopEntities(size: Int): List<ShopEntity>
}