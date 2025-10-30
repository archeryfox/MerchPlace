package com.example.merchplace.domain.repository

import com.example.merchplace.domain.entities.Lottery
import com.example.merchplace.domain.entities.LotteryTicket
import kotlinx.coroutines.flow.Flow

interface LotteryRepository {
    fun getLotteries(status: String? = null): Flow<List<Lottery>>
    suspend fun getLotteryById(id: Int): Lottery?
    suspend fun buyTicket(lotteryId: Int, quantity: Int): Result<List<LotteryTicket>>
    fun getMyTickets(lotteryId: Int): Flow<List<LotteryTicket>>
}

