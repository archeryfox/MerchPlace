package com.example.merchplace.presentation.screens.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.Product
import com.example.merchplace.domain.repository.ProductRepository
import com.example.merchplace.shared.di.RepositoryProvider
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShopViewModel(
    private val repository: ProductRepository = RepositoryProvider.productRepository
) : ViewModel() {
    
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private var loadJob: Job? = null
    
    init {
        loadProducts()
    }
    
    fun loadProducts(category: String? = null) {
        // Cancel previous loading job if exists
        loadJob?.cancel()
        
        loadJob = viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.getProducts(category).collect { productList ->
                    _products.value = productList
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        loadJob?.cancel()
    }
}

