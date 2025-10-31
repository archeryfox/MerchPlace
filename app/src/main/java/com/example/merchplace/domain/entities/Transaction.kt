package com.example.merchplace.domain.entities

data class Transaction(
    val id: Int,
    val userId: Int,
    val amount: Int,
    val type: TransactionType,
    val description: String,
    val createdAt: Long
)

enum class TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    REFUND
}

