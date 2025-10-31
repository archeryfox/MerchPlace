package com.example.merchplace.domain.entities

data class Order(
    val id: Int,
    val items: List<CartItem>,
    val totalPrice: Int,
    val status: OrderStatus,
    val createdAt: Long,
    val userId: Int
)

enum class OrderStatus {
    PENDING,
    PROCESSING,
    COMPLETED,
    CANCELLED
}

