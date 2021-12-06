package com.example.number.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.number.modules.SessionManager
import com.example.number.repository.BinaryGroupDataSource
import com.example.number.viewmodels.states.BinsState

class BinaryNumbersViewModel(
    private val sessionManager: SessionManager,
    private var binaryGroupDataSource: BinaryGroupDataSource
) : ViewModel() {
    private val _state = MutableLiveData<BinsState>()
    val state: LiveData<BinsState>
        get() = _state

    init{
        if(sessionManager.isBinsFirstOpen){
            _state.postValue(BinsState.FirstBinsOpen)
            sessionManager.isBinsFirstOpen = false
        }
    }

    var binaryGroups = Pager(PagingConfig(15)) { binaryGroupDataSource }
        .flow.cachedIn(viewModelScope)

    fun saveGroupId(id: Int) {
        sessionManager.groupId = id
    }
}