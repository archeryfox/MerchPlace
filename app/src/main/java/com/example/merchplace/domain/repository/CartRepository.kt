package com.example.merchplace.domain.repository

import com.example.merchplace.domain.entities.CartItem
import kotlinx.coroutines.flow.StateFlow

interface CartRepository {
    fun getCartItems(): StateFlow<List<CartItem>>
    suspend fun addItem(item: CartItem)
    suspend fun removeItem(itemId: Int)
    suspend fun updateQuantity(itemId: Int, quantity: Int)
    suspend fun clearCart()
    suspend fun getTotalPrice(): Int
    suspend fun getTotalItems(): Int
}

