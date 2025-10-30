package com.example.merchplace.domain.repository

import com.example.merchplace.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getCurrentUser(): User?
    suspend fun getUserById(id: Int): User?
    fun getUsers(): Flow<List<User>>
}

