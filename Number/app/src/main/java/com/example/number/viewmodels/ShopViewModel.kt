package com.example.number.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.number.model.ShopEntity
import com.example.number.modules.SessionManager
import com.example.number.repository.NumbersRepository
import com.example.number.repository.ShopPagingDataSource
import kotlinx.coroutines.flow.*

class ShopViewModel(
    private val sessionManager: SessionManager,
    private val repository: NumbersRepository,
    private val shopPagingDataSource: ShopPagingDataSource
) : ViewModel() {
    private var shopEntities = mutableListOf<ShopEntity>()
    private val _state = MutableLiveData<ShopState>()
    val characters = Pager(PagingConfig(10)) { shopPagingDataSource }
        .flow.cachedIn(viewModelScope)
    val state: LiveData<ShopState>
        get() = _state
    init {
        _state.postValue(ShopState.Loading)
        _state.postValue(ShopState.Shop)
    }
}