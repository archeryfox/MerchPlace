package com.example.merchplace.domain.entities

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val avatar: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val itemsSold: Int,
    val rating: Double,
    val verified: Boolean,
    val isCreator: Boolean,
    val categories: List<String>,
    val location: String,
    val donationEnabled: Boolean = false,
    val donationGoal: Int = 0,
    val donationCurrent: Int = 0
)

