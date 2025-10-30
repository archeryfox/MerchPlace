package com.example.merchplace.domain.entities

data class CartItem(
    val id: Int,
    val title: String,
    val price: Int,
    val quantity: Int,
    val image: String,
    val seller: User
)

