package com.example.merchplace.data.repository

import com.example.merchplace.data.datasource.mock.MockUsers
import com.example.merchplace.domain.entities.Transaction
import com.example.merchplace.domain.entities.TransactionType
import com.example.merchplace.domain.repository.IBalanceRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BalanceRepository : IBalanceRepository {
    
    private val userBalances = MutableStateFlow<Map<Int, Int>>(
        MockUsers.users.associate { it.id to it.balance }
    )
    
    private val _transactions = MutableStateFlow<Map<Int, MutableList<Transaction>>>(
        MockUsers.users.associate { it.id to mutableListOf<Transaction>() }
    )
    
    private var nextTransactionId = 1
    
    override suspend fun getBalance(userId: Int): Int {
        delay(100)
        return userBalances.value[userId] ?: 0
    }
    
    override suspend fun updateBalance(userId: Int, amount: Int): Boolean {
        delay(200)
        val currentBalances = userBalances.value.toMutableMap()
        val currentBalance = currentBalances[userId] ?: 0
        val newBalance = currentBalance + amount
        
        if (newBalance < 0) {
            return false
        }
        
        currentBalances[userId] = newBalance
        userBalances.value = currentBalances
        return true
    }
    
    override fun getTransactions(userId: Int): Flow<List<Transaction>> {
        return _transactions.value[userId]?.let { list ->
            kotlinx.coroutines.flow.flowOf(list)
        } ?: kotlinx.coroutines.flow.flowOf(emptyList())
    }
    
    override suspend fun addTransaction(transaction: Transaction) {
        delay(100)
        val currentTransactions = _transactions.value.toMutableMap()
        val userTransactions = currentTransactions[transaction.userId]?.toMutableList() ?: mutableListOf()
        userTransactions.add(transaction)
        currentTransactions[transaction.userId] = userTransactions
        _transactions.value = currentTransactions
    }
    
    fun getNextTransactionId(): Int {
        return nextTransactionId++
    }
}

