package com.example.merchplace.presentation.screens.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.Order
import com.example.merchplace.domain.repository.IOrderRepository
import com.example.merchplace.shared.di.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OrderHistoryViewModel(
    private val orderRepository: IOrderRepository = RepositoryProvider.orderRepository,
    private val userId: Int = 1
) : ViewModel() {
    
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadOrders()
    }
    
    private fun loadOrders() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                orderRepository.getOrders(userId).collect { orderList ->
                    _orders.value = orderList.sortedByDescending { it.createdAt }
                }
            } catch (e: Exception) {
                _orders.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}

