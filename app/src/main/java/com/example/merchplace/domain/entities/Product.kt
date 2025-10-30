package com.example.merchplace.domain.entities

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val price: Int,
    val stock: Int,
    val category: String,
    val seller: User,
    val rating: Double,
    val reviews: Int
)

