package com.example.number.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.number.model.BinaryNumberDB
import com.example.number.model.BinaryNumberGroup
import com.example.number.modules.SessionManager
import com.example.number.repository.BinaryGroupDataSource
import com.example.number.repository.NumbersRepository
import kotlinx.coroutines.launch

class BinaryNumbersViewModel(
    private val sessionManager: SessionManager,
    private val repository: NumbersRepository,
    private var binaryGroupDataSource: BinaryGroupDataSource
) : ViewModel() {
    var binaryGroups = Pager(PagingConfig(15)) { binaryGroupDataSource }
        .flow.cachedIn(viewModelScope)

    fun saveGroupId(id: Int){
        sessionManager.groupId = id
    }
}