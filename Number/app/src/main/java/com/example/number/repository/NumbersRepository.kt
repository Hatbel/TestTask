package com.example.number.repository

import com.example.number.database.BinaryGroupDao
import com.example.number.database.BinaryNumberDao
import com.example.number.database.ShopDao
import com.example.number.model.BinaryNumberDB
import com.example.number.model.BinaryNumberGroup
import com.example.number.model.ShopEntity
import com.example.number.modules.SessionManager

class NumbersRepository(
    private val sessionManager: SessionManager,
    private val shopDao: ShopDao,
    private val binaryNumberDao: BinaryNumberDao,
    private val binaryGroupDao: BinaryGroupDao
) {
    suspend fun getShopEntityById(id: Int) = shopDao.getShopEntityById(id)
    suspend fun updateShopEntity(shopEntity: ShopEntity) = shopDao.updateShopEntity(shopEntity)
    suspend fun getAllShopEntities(limit: Int) = shopDao.getShopEntities(limit)
    suspend fun getNumberByNumber(binaryNumber: String) = binaryNumberDao.getBinaryNumbByNumber(binaryNumber)
    suspend fun updateBinaryNumber(binaryNumber: BinaryNumberDB) = binaryNumberDao.updateBinaryNumber(binaryNumber)
    suspend fun getNumbersByGroupId(groupId: Int) = binaryNumberDao.getNumbersByGroupId(groupId)
    suspend fun getGroupById(groupId: Int) = binaryGroupDao.getBinaryNumberGroupById(groupId)
    suspend fun updateGroupId(group: BinaryNumberGroup) = binaryGroupDao.updateGroup(group)
}