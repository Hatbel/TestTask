package com.example.number.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.number.model.BinaryNumberDB
import com.example.number.model.ShopEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BinaryNumberDao {
    @Query("SELECT * FROM BinaryNumberDB where id = :id")
    suspend fun getBinaryNumbsById(id: Int): BinaryNumberDB

    @Query("SELECT * FROM BinaryNumberDB where binaryNumber = :binaryNumber")
    suspend fun getBinaryNumbByNumber(binaryNumber: String): BinaryNumberDB

    @Query("SELECT * FROM BinaryNumberDB where isFound = :isFound")
    suspend fun getFoundBooks(isFound: Boolean): List<BinaryNumberDB>

    @Query("SELECT * FROM BinaryNumberDB where groupId = :id LIMIT :size")
    suspend fun getNumbersByGroupId(size: Int, id: Int): List<BinaryNumberDB>

    @Query("SELECT * FROM BinaryNumberDB where groupId = :groupId")
    suspend fun getNumbersByGroupId(groupId: Int): List<BinaryNumberDB>

    @Query("SELECT * FROM BinaryNumberDB")
    fun getBooks(): Flow<List<BinaryNumberDB>>

    @Update
    suspend fun updateBinaryNumber(binaryNumber: BinaryNumberDB)
}