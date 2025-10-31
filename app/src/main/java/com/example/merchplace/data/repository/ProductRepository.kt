package com.example.merchplace.data.repository

import com.example.merchplace.data.datasource.mock.MockProducts
import com.example.merchplace.domain.entities.Product
import com.example.merchplace.domain.repository.IProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository : IProductRepository {
    
    override fun getProducts(category: String?): Flow<List<Product>> = flow {
        delay(500)
        val products = if (category != null) {
            MockProducts.products.filter { it.category == category }
        } else {
            MockProducts.products
        }
        emit(products)
    }
    
    override suspend fun getProductById(id: Int): Product? {
        delay(300)
        return MockProducts.products.find { it.id == id }
    }
    
    override fun searchProducts(query: String): Flow<List<Product>> = flow {
        delay(400)
        val filtered = MockProducts.products.filter {
            it.title.contains(query, ignoreCase = true) ||
            it.description.contains(query, ignoreCase = true)
        }
        emit(filtered)
    }
}

