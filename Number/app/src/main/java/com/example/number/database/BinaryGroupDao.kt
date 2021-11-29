package com.example.number.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.number.model.BinaryNumberGroup

@Dao
interface BinaryGroupDao {
    @Query("SELECT * FROM BinaryNumberGroup LIMIT :size")
    suspend fun getBinaryNumbersGroups(size: Int): List<BinaryNumberGroup>

    @Query("SELECT * FROM BinaryNumberGroup where groupId = :groupId")
    suspend fun getBinaryNumberGroupById(groupId: Int): BinaryNumberGroup

    @Update
    suspend fun updateGroup(group: BinaryNumberGroup)
}