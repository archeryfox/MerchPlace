package com.example.merchplace.data.repository

import com.example.merchplace.domain.entities.Transaction
import com.example.merchplace.domain.entities.TransactionType
import com.example.merchplace.domain.repository.IBalanceRepository
import com.example.merchplace.domain.repository.IPaymentRepository
import com.example.merchplace.domain.repository.PaymentResult
import kotlinx.coroutines.delay

class PaymentRepository(
    private val balanceRepository: BalanceRepository
) : IPaymentRepository {
    
    override suspend fun processPayment(userId: Int, amount: Int): PaymentResult {
        delay(300)
        
        val currentBalance = balanceRepository.getBalance(userId)
        
        if (currentBalance < amount) {
            return PaymentResult(
                success = false,
                message = "Недостаточно средств на балансе"
            )
        }
        
        val success = balanceRepository.updateBalance(userId, -amount)
        
        if (success) {
            val transaction = Transaction(
                id = balanceRepository.getNextTransactionId(),
                userId = userId,
                amount = -amount,
                type = TransactionType.WITHDRAWAL,
                description = "Оплата заказа на сумму ${amount}₽",
                createdAt = System.currentTimeMillis()
            )
            balanceRepository.addTransaction(transaction)
            
            return PaymentResult(
                success = true,
                message = "Платёж успешно выполнен",
                transactionId = transaction.id
            )
        }
        
        return PaymentResult(
            success = false,
            message = "Ошибка при обработке платежа"
        )
    }
}

