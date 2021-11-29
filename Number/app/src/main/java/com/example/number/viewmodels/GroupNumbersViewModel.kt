package com.example.number.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.number.modules.SessionManager
import com.example.number.repository.BinaryNumbersPagingDataSource
import com.example.number.repository.NumbersRepository

class GroupNumbersViewModel(
    private var binaryNumbersDataSource: BinaryNumbersPagingDataSource
) : ViewModel() {
    var binaryNumbers = Pager(PagingConfig(25)) { binaryNumbersDataSource }
        .flow.cachedIn(viewModelScope)
}