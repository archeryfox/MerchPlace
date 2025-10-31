package com.example.merchplace.presentation.features.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.CartItem
import com.example.merchplace.domain.repository.ICartRepository
import com.example.merchplace.shared.di.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: ICartRepository = RepositoryProvider.cartRepository
) : ViewModel() {
    
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()
    
    private val _totalPrice = MutableStateFlow(0)
    val totalPrice: StateFlow<Int> = _totalPrice.asStateFlow()
    
    private val _totalItems = MutableStateFlow(0)
    val totalItems: StateFlow<Int> = _totalItems.asStateFlow()
    
    init {
        observeCart()
    }
    
    private fun observeCart() {
        viewModelScope.launch {
            repository.getCartItems().collect { items ->
                _cartItems.value = items
                _totalPrice.value = repository.getTotalPrice()
                _totalItems.value = repository.getTotalItems()
            }
        }
    }
    
    fun addItem(item: CartItem) {
        viewModelScope.launch {
            repository.addItem(item)
        }
    }
    
    fun addItem(
        id: Int,
        title: String,
        price: Int,
        image: String,
        seller: com.example.merchplace.domain.entities.User
    ) {
        viewModelScope.launch {
            val item = CartItem(
                id = id,
                title = title,
                price = price,
                quantity = 1,
                image = image,
                seller = seller
            )
            repository.addItem(item)
        }
    }
    
    fun removeItem(itemId: Int) {
        viewModelScope.launch {
            repository.removeItem(itemId)
        }
    }
    
    fun updateQuantity(itemId: Int, quantity: Int) {
        viewModelScope.launch {
            repository.updateQuantity(itemId, quantity)
        }
    }
    
    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }
}

