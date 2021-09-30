package com.example.number.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.number.database.ShopDao
import com.example.number.model.ShopEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShopPagingDataSource(private val shopDao: ShopDao) : PagingSource<Int, ShopEntity>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShopEntity> {
        val position = params.key ?: INITIAL_PAGE_INDEX
        val randomPosts: List<ShopEntity>
        withContext(Dispatchers.IO) {
            randomPosts = shopDao.getShopEntities(params.loadSize)
        }
        return try {
            LoadResult.Page(
                data = randomPosts,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (randomPosts.isNullOrEmpty()) null else position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ShopEntity>): Int? = state.anchorPosition
}