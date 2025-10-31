package com.example.merchplace.data.repository

import com.example.merchplace.data.datasource.mock.MockLotteries
import com.example.merchplace.domain.entities.Lottery
import com.example.merchplace.domain.entities.LotteryTicket
import com.example.merchplace.domain.repository.ILotteryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.Date

class LotteryRepository : ILotteryRepository {
    
    private val tickets = mutableListOf<LotteryTicket>()
    // Mutex для синхронизации операций с билетами и предотвращения race conditions
    private val ticketsMutex = Mutex()
    
    override fun getLotteries(status: String?): Flow<List<Lottery>> = flow {
        delay(500)
        val lotteries = MockLotteries.lotteries
        emit(lotteries)
    }
    
    override suspend fun getLotteryById(id: Int): Lottery? {
        delay(300)
        return MockLotteries.lotteries.find { it.id == id }
    }
    
    override suspend fun buyTicket(lotteryId: Int, quantity: Int): Result<List<LotteryTicket>> {
        delay(800)
        val lottery = MockLotteries.lotteries.find { it.id == lotteryId }
        
        if (lottery == null) {
            return Result.failure(Exception("Лотерея не найдена"))
        }
        
        // Используем Mutex для атомарной проверки доступности и покупки билетов
        return ticketsMutex.withLock {
            // Вычисляем фактически проданные билеты динамически из списка билетов
            val lotteryTickets = tickets.filter { it.lotteryId == lotteryId }
            val actualTicketsSold = lotteryTickets.size
            val nextTicketNumber = actualTicketsSold + 1
            
            // Проверяем доступность билетов атомарно
            if (actualTicketsSold + quantity <= lottery.maxTickets) {
                // Используем max ID + 1 для избежания race conditions при параллельных покупках
                val maxId = tickets.maxOfOrNull { it.id } ?: 0
                val newTickets = (1..quantity).map { index ->
                    LotteryTicket(
                        id = maxId + index,
                        lotteryId = lotteryId,
                        userId = 1, // Mock current user
                        ticketNumber = nextTicketNumber + index - 1,
                        purchaseDate = Date()
                    )
                }
                tickets.addAll(newTickets)
                Result.success(newTickets)
            } else {
                val available = lottery.maxTickets - actualTicketsSold
                Result.failure(Exception("Недостаточно билетов. Доступно: $available из ${lottery.maxTickets}"))
            }
        }
    }
    
    override fun getMyTickets(lotteryId: Int): Flow<List<LotteryTicket>> = flow {
        delay(300)
        emit(tickets.filter { it.lotteryId == lotteryId && it.userId == 1 })
    }
}

