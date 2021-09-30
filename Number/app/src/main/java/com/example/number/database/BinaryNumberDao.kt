package com.example.number.database

import androidx.room.Dao
import androidx.room.Query
import com.example.number.model.BinaryNumberDB
import kotlinx.coroutines.flow.Flow

@Dao
interface BinaryNumberDao {
    @Query("SELECT * FROM BinaryNumberDB where id = :id")
    suspend fun getBinaryNumbsById(id: Int): BinaryNumberDB

    @Query("SELECT * FROM BinaryNumberDB where isFound = :isFound")
    suspend fun getFoundBooks(isFound: Boolean): List<BinaryNumberDB>

    @Query("SELECT * FROM BinaryNumberDB where groupId = :id")
    suspend fun getNumbersByGroupId(id: Int): List<BinaryNumberDB>

    @Query("SELECT * FROM BinaryNumberDB where groupId = :id and isFound = :isFound")
    suspend fun getFoundNumbersFromCategory(id: Int, isFound: Boolean): List<BinaryNumberDB>

    @Query("SELECT * FROM BinaryNumberDB")
    fun getBooks(): Flow<List<BinaryNumberDB>>
}