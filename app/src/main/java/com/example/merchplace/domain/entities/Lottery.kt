package com.example.merchplace.domain.entities

import java.util.Date

data class Lottery(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val ticketPrice: Int,
    val maxTickets: Int,
    val ticketsSold: Int,
    val drawTime: Date,
    val status: LotteryStatus,
    val prize: String,
    val category: String
)

enum class LotteryStatus {
    ACTIVE,
    DRAWN,
    CANCELLED
}

data class LotteryTicket(
    val id: Int,
    val lotteryId: Int,
    val userId: Int,
    val ticketNumber: Int,
    val purchaseDate: Date
)

