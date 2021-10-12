package com.example.number.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BinaryNumberGroup(
    @PrimaryKey
    var groupId: Int,
    var isCollected: Boolean = false,
    var collectedAmount: Int,
    var isBusterActive: Boolean = false,
    var busterName: String,
    var buster: String
)