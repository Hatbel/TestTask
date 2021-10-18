package com.example.number.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.number.model.BinaryNumberDB
import com.example.number.modules.SessionManager
import com.example.number.repository.BinaryGroupDataSource
import com.example.number.repository.NumbersRepository
import kotlinx.coroutines.launch

class BinaryNumbersViewModel(
    private val sessionManager: SessionManager,
    private val repository: NumbersRepository,
    private var binaryGroupDataSource: BinaryGroupDataSource
) : ViewModel() {
    var binaryGroups = Pager(PagingConfig(20)) { binaryGroupDataSource }
        .flow.cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            for(groupId in 1..40){
                var isFoundAmount = 0
                val numbersInGroup = repository.getNumbersByGroupId(groupId)
                for(number: BinaryNumberDB in numbersInGroup)
                    if(number.isFound) isFoundAmount+=1
                if(isFoundAmount == 25){
                    val group = repository.getGroupById(groupId)
                    group.isCollected = true
                    repository.updateGroupId(group)
                }
            }
        }
    }

    fun saveGroupId(id: Int){
        sessionManager.groupId = id
    }
}