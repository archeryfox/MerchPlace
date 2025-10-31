package com.example.merchplace.domain.repository

import com.example.merchplace.domain.entities.Order
import kotlinx.coroutines.flow.Flow

interface IOrderRepository {
    suspend fun createOrder(items: List<com.example.merchplace.domain.entities.CartItem>, userId: Int): Order
    fun getOrders(userId: Int): Flow<List<Order>>
    suspend fun getOrderById(orderId: Int): Order?
    suspend fun updateOrderStatus(orderId: Int, status: com.example.merchplace.domain.entities.OrderStatus)
}

