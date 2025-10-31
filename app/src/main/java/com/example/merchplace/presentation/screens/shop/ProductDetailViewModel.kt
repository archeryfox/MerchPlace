package com.example.merchplace.presentation.screens.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.Product
import com.example.merchplace.domain.repository.IProductRepository
import com.example.merchplace.shared.di.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val repository: IProductRepository = RepositoryProvider.productRepository
) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadProduct(productId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _product.value = repository.getProductById(productId)
            } catch (e: Exception) {
                _product.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}

