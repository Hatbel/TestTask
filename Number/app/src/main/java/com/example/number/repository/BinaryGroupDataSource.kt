package com.example.number.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.number.database.BinaryGroupDao
import com.example.number.database.ShopDao
import com.example.number.model.BinaryNumberGroup
import com.example.number.model.ShopEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BinaryGroupDataSource(private val binGroupsDao: BinaryGroupDao) : PagingSource<Int, BinaryNumberGroup>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BinaryNumberGroup> {
        val position = params.key ?: INITIAL_PAGE_INDEX
        val binGroups: List<BinaryNumberGroup> = binGroupsDao.getBinaryNumbersGroups(params.loadSize)
        val nextKey =
            if (binGroups.isEmpty()) {
                null
            } else {
                position + params.loadSize
            }
        return try {
            LoadResult.Page(
                data = binGroups,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BinaryNumberGroup>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}