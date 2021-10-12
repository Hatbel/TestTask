package com.example.number.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.number.database.BinaryGroupDao
import com.example.number.database.BinaryNumberDao
import com.example.number.model.BinaryNumberDB
import com.example.number.model.BinaryNumberGroup
import com.example.number.modules.SessionManager

class BinaryNumbersPagingDataSource(private val binNumbersDao: BinaryNumberDao, private val sessionManager: SessionManager) : PagingSource<Int, BinaryNumberDB>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BinaryNumberDB> {
        val position = params.key ?: INITIAL_PAGE_INDEX
        val binGroups: List<BinaryNumberDB> = binNumbersDao.getNumbersByGroupId(params.loadSize,sessionManager.groupId)
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

    override fun getRefreshKey(state: PagingState<Int, BinaryNumberDB>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}