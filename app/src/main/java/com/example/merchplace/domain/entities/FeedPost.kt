package com.example.merchplace.domain.entities

import java.util.Date

data class FeedPost(
    val id: Int,
    val userId: Int,
    val userName: String,
    val userAvatar: String,
    val content: String,
    val images: List<String>,
    val likes: Int,
    val comments: Int,
    val timestamp: Date,
    val type: String = "post",
    val liked: Boolean = false
)

