package com.example.merchplace.data.datasource.mock

import com.example.merchplace.domain.entities.User

object MockUsers {
    val users = listOf(
        User(
            id = 1,
            name = "Алексей Волков",
            username = "@alexwolf",
            avatar = "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/artist-portrait.png",
            bio = "Художник и создатель уникальных артов. Специализируюсь на фурри-арте и комиксах.",
            followers = 1234,
            following = 567,
            itemsSold = 89,
            rating = 4.9,
            verified = true,
            isCreator = true,
            categories = listOf("Фурри-арт", "Комиксы", "Цифровое искусство"),
            location = "Павильон A, Стенд 42",
            donationEnabled = true,
            donationGoal = 50000,
            donationCurrent = 32500,
            balance = 5000
        ),
        User(
            id = 2,
            name = "Мария Светлова",
            username = "@maria_art",
            avatar = "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/female-artist.png",
            bio = "Создаю уникальные коллекционные фигурки и мерч по мотивам аниме и игр.",
            followers = 2341,
            following = 234,
            itemsSold = 156,
            rating = 5.0,
            verified = true,
            isCreator = true,
            categories = listOf("Фигурки", "Аниме", "Мерч"),
            location = "Павильон B, Стенд 15",
            donationEnabled = true,
            donationGoal = 75000,
            donationCurrent = 68000,
            balance = 7500
        )
    )
}

