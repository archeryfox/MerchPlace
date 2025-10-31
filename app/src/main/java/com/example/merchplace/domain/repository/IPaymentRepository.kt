package com.example.merchplace.domain.repository

interface IPaymentRepository {
    suspend fun processPayment(userId: Int, amount: Int): PaymentResult
}

data class PaymentResult(
    val success: Boolean,
    val message: String,
    val transactionId: Int? = null
)

