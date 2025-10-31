package com.example.merchplace.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.User
import com.example.merchplace.domain.repository.IBalanceRepository
import com.example.merchplace.domain.repository.IUserRepository
import com.example.merchplace.shared.di.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: IUserRepository = RepositoryProvider.userRepository,
    private val balanceRepository: IBalanceRepository = RepositoryProvider.balanceRepository
) : ViewModel() {
    
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()
    
    private val _balance = MutableStateFlow<Int?>(null)
    val balance: StateFlow<Int?> = _balance.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadCurrentUser()
    }
    
    private fun loadCurrentUser() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val user = userRepository.getCurrentUser()
                _currentUser.value = user
                
                user?.let {
                    _balance.value = balanceRepository.getBalance(it.id)
                }
            } catch (e: Exception) {
                _currentUser.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun refreshBalance() {
        viewModelScope.launch {
            _currentUser.value?.let {
                _balance.value = balanceRepository.getBalance(it.id)
            }
        }
    }
}

