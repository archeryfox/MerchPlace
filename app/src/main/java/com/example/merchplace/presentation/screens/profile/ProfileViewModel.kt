package com.example.merchplace.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.User
import com.example.merchplace.domain.repository.UserRepository
import com.example.merchplace.shared.di.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository = RepositoryProvider.userRepository
) : ViewModel() {
    
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadCurrentUser()
    }
    
    private fun loadCurrentUser() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _currentUser.value = userRepository.getCurrentUser()
            } catch (e: Exception) {
                _currentUser.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}

