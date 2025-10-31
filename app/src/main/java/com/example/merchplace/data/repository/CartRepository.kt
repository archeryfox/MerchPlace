package com.example.merchplace.data.repository

import com.example.merchplace.domain.entities.CartItem
import com.example.merchplace.domain.repository.ICartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartRepository : ICartRepository {
    
    companion object {
        @Volatile
        private var INSTANCE: CartRepository? = null
        
        fun getInstance(): CartRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: CartRepository().also { INSTANCE = it }
            }
        }
    }
    
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    
    override fun getCartItems(): StateFlow<List<CartItem>> = _cartItems.asStateFlow()
    
    override suspend fun addItem(item: CartItem) {
        val currentItems = _cartItems.value.toMutableList()
        val existingItem = currentItems.find { it.id == item.id }
        
        if (existingItem != null) {
            val index = currentItems.indexOf(existingItem)
            currentItems[index] = existingItem.copy(quantity = existingItem.quantity + 1)
        } else {
            currentItems.add(item)
        }
        
        _cartItems.value = currentItems
    }
    
    override suspend fun removeItem(itemId: Int) {
        _cartItems.value = _cartItems.value.filter { it.id != itemId }
    }
    
    override suspend fun updateQuantity(itemId: Int, quantity: Int) {
        if (quantity <= 0) {
            removeItem(itemId)
            return
        }
        
        _cartItems.value = _cartItems.value.map { item ->
            if (item.id == itemId) {
                item.copy(quantity = quantity)
            } else {
                item
            }
        }
    }
    
    override suspend fun clearCart() {
        _cartItems.value = emptyList()
    }
    
    override suspend fun getTotalPrice(): Int {
        return _cartItems.value.sumOf { it.price * it.quantity }
    }
    
    override suspend fun getTotalItems(): Int {
        return _cartItems.value.sumOf { it.quantity }
    }
}

