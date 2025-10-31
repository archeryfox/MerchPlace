package com.example.merchplace.presentation.screens.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.CartItem
import com.example.merchplace.domain.entities.Order
import com.example.merchplace.domain.repository.ICartRepository
import com.example.merchplace.domain.repository.IOrderRepository
import com.example.merchplace.domain.repository.IPaymentRepository
import com.example.merchplace.shared.di.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val cartRepository: ICartRepository = RepositoryProvider.cartRepository,
    private val orderRepository: IOrderRepository = RepositoryProvider.orderRepository,
    private val paymentRepository: IPaymentRepository = RepositoryProvider.paymentRepository,
    private val userId: Int = 1
) : ViewModel() {
    
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()
    
    private val _totalPrice = MutableStateFlow(0)
    val totalPrice: StateFlow<Int> = _totalPrice.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    private val _orderCreated = MutableStateFlow<Order?>(null)
    val orderCreated: StateFlow<Order?> = _orderCreated.asStateFlow()
    
    init {
        loadCartData()
    }
    
    private fun loadCartData() {
        viewModelScope.launch {
            cartRepository.getCartItems().collect { items ->
                _cartItems.value = items
                _totalPrice.value = cartRepository.getTotalPrice()
            }
        }
    }
    
    fun processOrder(onSuccess: (Order) -> Unit) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                
                val items = _cartItems.value
                if (items.isEmpty()) {
                    _error.value = "Корзина пуста"
                    _isLoading.value = false
                    return@launch
                }
                
                val total = _totalPrice.value
                
                val paymentResult = paymentRepository.processPayment(userId, total)
                
                if (!paymentResult.success) {
                    _error.value = paymentResult.message
                    _isLoading.value = false
                    return@launch
                }
                
                val order = orderRepository.createOrder(items, userId)
                
                orderRepository.updateOrderStatus(order.id, com.example.merchplace.domain.entities.OrderStatus.PROCESSING)
                
                cartRepository.clearCart()
                
                _orderCreated.value = order
                onSuccess(order)
            } catch (e: Exception) {
                _error.value = "Ошибка при оформлении заказа: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

