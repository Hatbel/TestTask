package com.example.number.database

import androidx.room.Dao
import androidx.room.Query
import com.example.number.model.BinaryNumberGroup
import com.example.number.model.ShopEntity

@Dao
interface BinaryGroupDao {
    @Query("SELECT * FROM BinaryNumberGroup LIMIT :size")
    suspend fun getBinaryNumbersGroups(size: Int): List<BinaryNumberGroup>
}