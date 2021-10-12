package com.example.number.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.number.model.ShopEntity


@Dao
interface ShopDao {
    @Query("SELECT * FROM ShopEntity LIMIT :size")
    suspend fun getShopEntities(size: Int): List<ShopEntity>

    @Query("SELECT * FROM ShopEntity where id = :id")
    suspend fun getShopEntityById(id: Int): ShopEntity

    @Update
    suspend fun updateShopEntity(shopEntity: ShopEntity)
}