package com.example.merchplace.domain.repository

import com.example.merchplace.domain.entities.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getProducts(category: String? = null): Flow<List<Product>>
    suspend fun getProductById(id: Int): Product?
    fun searchProducts(query: String): Flow<List<Product>>
}

