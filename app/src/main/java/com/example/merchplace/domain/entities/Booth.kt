package com.example.merchplace.domain.entities

data class Booth(
    val id: Int,
    val name: String,
    val seller: User,
    val latitude: Double,
    val longitude: Double,
    val category: String
)

