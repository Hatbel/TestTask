package com.example.number.viewmodels.states

import com.example.number.model.ShopEntity

sealed class ShopState {
    data class Idle(val shopEntities: List<ShopEntity>) : ShopState()
    object Loading : ShopState()
    object PurchaseItem : ShopState()
    object EnableItem : ShopState()
    object NotEnoughNumbers : ShopState()
    data class Error(val error: String?) : ShopState()

}