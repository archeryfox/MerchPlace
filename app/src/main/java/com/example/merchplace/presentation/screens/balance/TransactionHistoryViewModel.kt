package com.example.merchplace.presentation.screens.balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.Transaction
import com.example.merchplace.domain.repository.IBalanceRepository
import com.example.merchplace.shared.di.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TransactionHistoryViewModel(
    private val balanceRepository: IBalanceRepository = RepositoryProvider.balanceRepository,
    private val userId: Int = 1
) : ViewModel() {
    
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadTransactions()
    }
    
    private fun loadTransactions() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                balanceRepository.getTransactions(userId).collect { transactionList ->
                    _transactions.value = transactionList.sortedByDescending { it.createdAt }
                }
            } catch (e: Exception) {
                _transactions.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}

