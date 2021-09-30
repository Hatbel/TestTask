package com.example.number.viewmodels

import androidx.paging.PagingData
import com.example.number.model.ShopEntity

sealed class ShopState {
    object Idle : ShopState()
    object Loading : ShopState()
    object Shop : ShopState()
    data class Error(val error: String?) : ShopState()

}