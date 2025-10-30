package com.example.merchplace.domain.repository

import com.example.merchplace.domain.entities.Auction
import com.example.merchplace.domain.entities.AuctionBid
import kotlinx.coroutines.flow.Flow

interface AuctionRepository {
    fun getAuctions(category: String? = null): Flow<List<Auction>>
    suspend fun getAuctionById(id: Int): Auction?
    suspend fun placeBid(auctionId: Int, amount: Int): Result<AuctionBid>
    fun getBids(auctionId: Int): Flow<List<AuctionBid>>
}

