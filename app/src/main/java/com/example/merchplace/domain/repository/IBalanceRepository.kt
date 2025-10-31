package com.example.merchplace.domain.repository

import com.example.merchplace.domain.entities.Transaction
import kotlinx.coroutines.flow.Flow

interface IBalanceRepository {
    suspend fun getBalance(userId: Int): Int
    suspend fun updateBalance(userId: Int, amount: Int): Boolean
    fun getTransactions(userId: Int): Flow<List<Transaction>>
    suspend fun addTransaction(transaction: Transaction)
}

