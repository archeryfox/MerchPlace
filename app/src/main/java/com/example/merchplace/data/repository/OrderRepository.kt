package com.example.merchplace.data.repository

import com.example.merchplace.domain.entities.CartItem
import com.example.merchplace.domain.entities.Order
import com.example.merchplace.domain.entities.OrderStatus
import com.example.merchplace.domain.repository.IOrderRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class OrderRepository : IOrderRepository {
    
    private val _orders = MutableStateFlow<Map<Int, MutableList<Order>>>(
        mutableMapOf()
    )
    
    private var nextOrderId = 1
    
    override suspend fun createOrder(items: List<CartItem>, userId: Int): Order {
        delay(300)
        
        val totalPrice = items.sumOf { it.price * it.quantity }
        val order = Order(
            id = nextOrderId++,
            items = items,
            totalPrice = totalPrice,
            status = OrderStatus.PENDING,
            createdAt = System.currentTimeMillis(),
            userId = userId
        )
        
        val currentOrders = _orders.value.toMutableMap()
        val userOrders = currentOrders[userId]?.toMutableList() ?: mutableListOf()
        userOrders.add(order)
        currentOrders[userId] = userOrders
        _orders.value = currentOrders
        
        return order
    }
    
    override fun getOrders(userId: Int): Flow<List<Order>> {
        return _orders.asStateFlow().map { it[userId] ?: emptyList() }
    }
    
    override suspend fun getOrderById(orderId: Int): Order? {
        delay(200)
        return _orders.value.values.flatten().find { it.id == orderId }
    }
    
    override suspend fun updateOrderStatus(orderId: Int, status: OrderStatus) {
        delay(200)
        val currentOrders = _orders.value.toMutableMap()
        
        currentOrders.forEach { (userId, orders) ->
            val orderIndex = orders.indexOfFirst { it.id == orderId }
            if (orderIndex != -1) {
                val updatedOrders = orders.toMutableList()
                updatedOrders[orderIndex] = updatedOrders[orderIndex].copy(status = status)
                currentOrders[userId] = updatedOrders
            }
        }
        
        _orders.value = currentOrders
    }
}

