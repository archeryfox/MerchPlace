package com.example.merchplace.data.repository

import com.example.merchplace.data.datasource.mock.MockAuctions
import com.example.merchplace.domain.entities.Auction
import com.example.merchplace.domain.entities.AuctionBid
import com.example.merchplace.domain.repository.IAuctionRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.Date

class AuctionRepository : IAuctionRepository {
    
    private val bids = mutableListOf<AuctionBid>()
    // Mutex для синхронизации операций со ставками и предотвращения race conditions
    private val bidsMutex = Mutex()
    
    override fun getAuctions(category: String?): Flow<List<Auction>> = flow {
        delay(500)
        val auctions = if (category != null) {
            MockAuctions.auctions.filter { it.category == category }
        } else {
            MockAuctions.auctions
        }
        emit(auctions)
    }
    
    override suspend fun getAuctionById(id: Int): Auction? {
        delay(300)
        return MockAuctions.auctions.find { it.id == id }
    }
    
    override suspend fun placeBid(auctionId: Int, amount: Int): Result<AuctionBid> {
        delay(800)
        val auction = MockAuctions.auctions.find { it.id == auctionId }
        
        if (auction == null) {
            return Result.failure(Exception("Аукцион не найден"))
        }
        
        // Используем Mutex для атомарной проверки и добавления ставки
        return bidsMutex.withLock {
            // Вычисляем текущую максимальную ставку динамически из списка ставок
            val auctionBids = bids.filter { it.auctionId == auctionId }
            val currentHighestBid = auctionBids.maxOfOrNull { it.amount } ?: auction.startingBid
            
            // Проверяем, что ставка строго больше текущей максимальной
            // Это предотвращает равенство ставок и гарантирует, что новая ставка всегда выше
            if (amount > currentHighestBid) {
                // Используем max ID + 1 для избежания race conditions при параллельных ставках
                val maxId = bids.maxOfOrNull { it.id } ?: 0
                val bid = AuctionBid(
                    id = maxId + 1,
                    auctionId = auctionId,
                    userId = 1, // Mock current user
                    amount = amount,
                    timestamp = Date()
                )
                bids.add(bid)
                Result.success(bid)
            } else {
                Result.failure(Exception("Ставка должна быть выше текущей (${currentHighestBid}₽)"))
            }
        }
    }
    
    override fun getBids(auctionId: Int): Flow<List<AuctionBid>> = flow {
        delay(300)
        emit(bids.filter { it.auctionId == auctionId })
    }
}

