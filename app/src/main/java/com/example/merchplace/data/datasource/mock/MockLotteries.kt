package com.example.merchplace.data.datasource.mock

import com.example.merchplace.domain.entities.Lottery
import com.example.merchplace.domain.entities.LotteryStatus
import java.util.Date

object MockLotteries {
    val lotteries = listOf(
        Lottery(
            id = 1,
            title = "Мега-розыгрыш: Коллекция редких фигурок",
            description = "Выиграй набор из 5 редких коллекционных фигурок общей стоимостью 15000₽!",
            image = "https://via.placeholder.com/800",
            ticketPrice = 100,
            maxTickets = 1000,
            ticketsSold = 743,
            drawTime = Date(System.currentTimeMillis() + 48 * 60 * 60 * 1000),
            status = LotteryStatus.ACTIVE,
            prize = "Набор из 5 фигурок",
            category = "Фигурки"
        ),
        Lottery(
            id = 2,
            title = "Розыгрыш эксклюзивного арта",
            description = "Получи оригинальный арт от известного художника!",
            image = "https://via.placeholder.com/800",
            ticketPrice = 200,
            maxTickets = 500,
            ticketsSold = 312,
            drawTime = Date(System.currentTimeMillis() + 72 * 60 * 60 * 1000),
            status = LotteryStatus.ACTIVE,
            prize = "Оригинальный арт 50x70",
            category = "Арт"
        )
    )
}

