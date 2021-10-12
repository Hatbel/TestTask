package com.example.number.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.number.model.ShopEntity
import com.example.number.modules.SessionManager
import com.example.number.repository.NumbersRepository
import kotlinx.coroutines.launch

class ShopViewModel(
    private val sessionManager: SessionManager,
    private val repository: NumbersRepository
) : ViewModel() {
    private var shopEntities = mutableListOf<ShopEntity>()
    private lateinit var shopEntity: ShopEntity
    private val _state = MutableLiveData<ShopState>()
    val state: LiveData<ShopState>
        get() = _state

    init {
        viewModelScope.launch {
            shopEntities.clear()
            shopEntities.addAll(repository.getAllShopEntities(25))
            _state.postValue(ShopState.Idle(shopEntities))
        }
    }

    fun purchase() {
        if (sessionManager.counterSaver >= shopEntity.value) {
            sessionManager.counterSaver -= shopEntity.value
            viewModelScope.launch {
                for (s: ShopEntity in shopEntities) {
                    if (s.id == shopEntity.id) {
                        s.isPurchased = true
                        shopEntity.isPurchased = true
                    }
                }
                sessionManager.buster = shopEntity.buster
                repository.updateShopEntity(shopEntity)
            }
            _state.postValue(ShopState.Idle(shopEntities))
            _state.postValue(ShopState.EnableItem)
        } else {
            _state.postValue(ShopState.NotEnoughNumbers)
        }
    }

    fun enableBuster() {
        viewModelScope.launch {
            for (s: ShopEntity in shopEntities) {
                if (s.id == shopEntity.id) {
                    s.isActive = true
                    shopEntity.isActive = true
                } else {
                    s.isActive = false
                    repository.updateShopEntity(s)
                }
            }
            repository.updateShopEntity(shopEntity)
            sessionManager.buster = shopEntity.buster
            _state.postValue(ShopState.Idle(shopEntities))
        }
    }

    fun isPurchased(id: Int) {
        viewModelScope.launch {
            shopEntity = repository.getShopEntityById(id)
            if (repository.getShopEntityById(id).isPurchased) {
                if (!repository.getShopEntityById(id).isActive) {
                    _state.postValue(ShopState.EnableItem)
                }
            } else {
                _state.postValue(ShopState.PurchaseItem)
            }
        }
    }
}