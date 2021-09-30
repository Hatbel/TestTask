package com.example.number.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopEntity(
    @PrimaryKey
    var id: Int,
    var name: String,
    var buster: Int,
    var value: Int,
    var isPurchased: Boolean = false,
    var isActive: Boolean = false
)