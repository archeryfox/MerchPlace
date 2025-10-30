package com.example.merchplace.data.datasource.mock

import com.example.merchplace.domain.entities.Auction
import java.util.Date

object MockAuctions {
    private val users = MockUsers.users
    
    val auctions = listOf(
        Auction(
            id = 1,
            title = "Редкая фигурка Sonic the Hedgehog Limited Edition 1/100",
            description = "Коллекционная фигурка Соника из лимитированной серии. Только 100 экземпляров в мире. Высота 30см, материал - высококачественный PVC. Включает сертификат подлинности и оригинальную упаковку.",
            images = listOf(
                "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/sonic-figure-collectible.jpg",
                "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/sonic-figure-box.jpg",
                "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/sonic-figure-detail.jpg"
            ),
            currentBid = 2500,
            startingBid = 1000,
            bids = 23,
            timeLeft = "2ч 34м",
            endTime = Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000 + 34 * 60 * 1000),
            category = "Фигурки",
            seller = users[0],
            condition = "Новое",
            rarity = "Легендарное",
            views = 1234
        ),
        Auction(
            id = 2,
            title = "Оригинальный арт-принт 'Лунный Волк' с автографом художника",
            description = "Уникальный принт размером 50x70см на премиум бумаге. Подписан художником. Тираж 25 экземпляров.",
            images = listOf(
                "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/wolf-moon-art-print.jpg",
                "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/art-print-signature.jpg"
            ),
            currentBid = 1200,
            startingBid = 500,
            bids = 15,
            timeLeft = "5ч 12м",
            endTime = Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000 + 12 * 60 * 1000),
            category = "Арт",
            seller = users[1],
            condition = "Новое",
            rarity = "Редкое",
            views = 892
        ),
        Auction(
            id = 3,
            title = "Винтажная футболка Comic-Con 2010 (размер M)",
            description = "Оригинальная футболка с Comic-Con 2010. Отличное состояние, без дефектов.",
            images = listOf("https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/comic-con-tshirt-vintage.jpg"),
            currentBid = 450,
            startingBid = 200,
            bids = 8,
            timeLeft = "1д 3ч",
            endTime = Date(System.currentTimeMillis() + 27 * 60 * 60 * 1000),
            category = "Одежда",
            seller = users[0],
            condition = "Отличное",
            rarity = "Редкое",
            views = 456
        )
    )
}

