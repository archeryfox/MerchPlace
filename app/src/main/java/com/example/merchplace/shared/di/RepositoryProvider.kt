package com.example.merchplace.shared.di

import com.example.merchplace.data.repository.AuctionRepositoryImpl
import com.example.merchplace.data.repository.CartRepositoryImpl
import com.example.merchplace.data.repository.LotteryRepositoryImpl
import com.example.merchplace.data.repository.ProductRepositoryImpl
import com.example.merchplace.data.repository.UserRepositoryImpl
import com.example.merchplace.domain.repository.AuctionRepository
import com.example.merchplace.domain.repository.CartRepository
import com.example.merchplace.domain.repository.LotteryRepository
import com.example.merchplace.domain.repository.ProductRepository
import com.example.merchplace.domain.repository.UserRepository

/**
 * Провайдер репозиториев для соблюдения Clean Architecture.
 * В будущем можно заменить на Dependency Injection (Hilt/Koin).
 */
object RepositoryProvider {
    
    // Singleton instances
    private val _productRepository: ProductRepository by lazy { ProductRepositoryImpl() }
    private val _auctionRepository: AuctionRepository by lazy { AuctionRepositoryImpl() }
    private val _cartRepository: CartRepository by lazy { CartRepositoryImpl.getInstance() }
    private val _userRepository: UserRepository by lazy { UserRepositoryImpl() }
    private val _lotteryRepository: LotteryRepository by lazy { LotteryRepositoryImpl() }
    
    // Public getters возвращают интерфейсы, а не реализации
    val productRepository: ProductRepository get() = _productRepository
    val auctionRepository: AuctionRepository get() = _auctionRepository
    val cartRepository: CartRepository get() = _cartRepository
    val userRepository: UserRepository get() = _userRepository
    val lotteryRepository: LotteryRepository get() = _lotteryRepository
}

