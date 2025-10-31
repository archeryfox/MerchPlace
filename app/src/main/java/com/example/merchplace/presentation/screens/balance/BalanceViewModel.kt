package com.example.merchplace.presentation.screens.balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.Transaction
import com.example.merchplace.domain.entities.TransactionType
import com.example.merchplace.domain.repository.IBalanceRepository
import com.example.merchplace.shared.di.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BalanceViewModel(
    private val balanceRepository: IBalanceRepository = RepositoryProvider.balanceRepository,
    private val userId: Int = 1
) : ViewModel() {
    
    private val _balance = MutableStateFlow<Int?>(null)
    val balance: StateFlow<Int?> = _balance.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    private val _depositAmount = MutableStateFlow("")
    val depositAmount: StateFlow<String> = _depositAmount.asStateFlow()
    
    init {
        loadBalance()
    }
    
    fun loadBalance() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _balance.value = balanceRepository.getBalance(userId)
            } catch (e: Exception) {
                _error.value = "Ошибка загрузки баланса"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun setDepositAmount(amount: String) {
        _depositAmount.value = amount
    }
    
    fun depositBalance(amount: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                
                if (amount <= 0) {
                    _error.value = "Сумма должна быть больше нуля"
                    _isLoading.value = false
                    return@launch
                }
                
                val success = balanceRepository.updateBalance(userId, amount)
                
                if (success) {
                    val balanceRepoImpl = balanceRepository as? com.example.merchplace.data.repository.BalanceRepository
                    val transaction = Transaction(
                        id = balanceRepoImpl?.getNextTransactionId() ?: 0,
                        userId = userId,
                        amount = amount,
                        type = TransactionType.DEPOSIT,
                        description = "Пополнение баланса на сумму ${amount}₽",
                        createdAt = System.currentTimeMillis()
                    )
                    balanceRepository.addTransaction(transaction)
                    
                    _depositAmount.value = ""
                    loadBalance()
                    onSuccess()
                } else {
                    _error.value = "Ошибка при пополнении баланса"
                }
            } catch (e: Exception) {
                _error.value = "Ошибка: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

