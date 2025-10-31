package com.example.merchplace.shared.di

import com.example.merchplace.data.repository.AuctionRepository
import com.example.merchplace.data.repository.BalanceRepository
import com.example.merchplace.data.repository.BoothRepository
import com.example.merchplace.data.repository.CartRepository
import com.example.merchplace.data.repository.LotteryRepository
import com.example.merchplace.data.repository.OrderRepository
import com.example.merchplace.data.repository.PaymentRepository
import com.example.merchplace.data.repository.ProductRepository
import com.example.merchplace.data.repository.UserRepository
import com.example.merchplace.domain.repository.IAuctionRepository
import com.example.merchplace.domain.repository.IBalanceRepository
import com.example.merchplace.domain.repository.IBoothRepository
import com.example.merchplace.domain.repository.ICartRepository
import com.example.merchplace.domain.repository.ILotteryRepository
import com.example.merchplace.domain.repository.IOrderRepository
import com.example.merchplace.domain.repository.IPaymentRepository
import com.example.merchplace.domain.repository.IProductRepository
import com.example.merchplace.domain.repository.IUserRepository

/**
 * Провайдер репозиториев для соблюдения Clean Architecture.
 * В будущем можно заменить на Dependency Injection (Hilt/Koin).
 */
object RepositoryProvider {
    
    // Singleton instances
    private val _productRepository: ProductRepository by lazy { ProductRepository() }
    private val _auctionRepository: AuctionRepository by lazy { AuctionRepository() }
    private val _cartRepository: CartRepository by lazy { CartRepository.getInstance() }
    private val _userRepository: UserRepository by lazy { UserRepository() }
    private val _lotteryRepository: LotteryRepository by lazy { LotteryRepository() }
    private val _balanceRepository: BalanceRepository by lazy { BalanceRepository() }
    private val _paymentRepository: PaymentRepository by lazy { PaymentRepository(_balanceRepository) }
    private val _orderRepository: OrderRepository by lazy { OrderRepository() }
    private val _boothRepository: BoothRepository by lazy { BoothRepository() }
    
    // Public getters возвращают интерфейсы, а не реализации
    val productRepository: IProductRepository get() = _productRepository
    val auctionRepository: IAuctionRepository get() = _auctionRepository
    val cartRepository: ICartRepository get() = _cartRepository
    val userRepository: IUserRepository get() = _userRepository
    val lotteryRepository: ILotteryRepository get() = _lotteryRepository
    val balanceRepository: IBalanceRepository get() = _balanceRepository
    val paymentRepository: IPaymentRepository get() = _paymentRepository
    val orderRepository: IOrderRepository get() = _orderRepository
    val boothRepository: IBoothRepository get() = _boothRepository
}
