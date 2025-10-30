package com.example.merchplace.data.datasource.mock

import com.example.merchplace.domain.entities.FeedPost
import java.util.Date

object MockFeedPosts {
    val posts = listOf(
        FeedPost(
            id = 1,
            userId = 1,
            userName = "Алексей Волков",
            userAvatar = "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/artist-portrait.png",
            content = "Только что завершил работу над новым артом! Скоро выставлю на аукцион 🎨",
            images = listOf("https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/furry-art-new.jpg"),
            likes = 234,
            comments = 45,
            timestamp = Date(System.currentTimeMillis() - 2 * 60 * 60 * 1000),
            type = "post"
        ),
        FeedPost(
            id = 2,
            userId = 2,
            userName = "Мария Арт",
            userAvatar = "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/female-artist.png",
            content = "Спасибо всем за поддержку! Мы достигли 90% цели по донатам! 💖",
            images = emptyList(),
            likes = 567,
            comments = 89,
            timestamp = Date(System.currentTimeMillis() - 5 * 60 * 60 * 1000),
            type = "announcement"
        ),
        FeedPost(
            id = 3,
            userId = 1,
            userName = "Алексей Волков",
            userAvatar = "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/artist-portrait.png",
            content = "Новая коллекция значков уже в магазине! Успейте купить, пока не разобрали!",
            images = listOf(
                "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/enamel-pins-collection.jpg",
                "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/pins-display.jpg"
            ),
            likes = 123,
            comments = 23,
            timestamp = Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000),
            type = "product"
        )
    )
}

