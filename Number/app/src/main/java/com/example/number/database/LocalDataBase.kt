package com.example.number.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.number.model.BinaryNumberDB
import com.example.number.model.BinaryNumberGroup
import com.example.number.model.ShopEntity

@Database(entities = [BinaryNumberDB::class, ShopEntity::class, BinaryNumberGroup::class], version = 1)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun binaryNumberDao(): BinaryNumberDao
    abstract fun shopDao(): ShopDao
    abstract fun binaryGroupDao(): BinaryGroupDao
}
