package com.example.merchplace.domain.entities

import java.util.Date

data class Auction(
    val id: Int,
    val title: String,
    val description: String,
    val images: List<String>,
    val currentBid: Int,
    val startingBid: Int,
    val bids: Int,
    val timeLeft: String,
    val endTime: Date,
    val category: String,
    val seller: User,
    val condition: String,
    val rarity: String,
    val views: Int
)

data class AuctionBid(
    val id: Int,
    val auctionId: Int,
    val userId: Int,
    val amount: Int,
    val timestamp: Date
)

