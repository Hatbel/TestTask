package com.example.number.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BinaryNumberDB(
    @PrimaryKey
    var id: Int,
    var binaryNumber: String,
    var isFound: Boolean = false,
    var groupId: Int
)