package com.example.merchplace.data.repository

import com.example.merchplace.data.datasource.mock.MockUsers
import com.example.merchplace.domain.entities.User
import com.example.merchplace.domain.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl : UserRepository {
    
    override suspend fun getCurrentUser(): User? {
        delay(300)
        return MockUsers.users.firstOrNull()
    }
    
    override suspend fun getUserById(id: Int): User? {
        delay(200)
        return MockUsers.users.find { it.id == id }
    }
    
    override fun getUsers(): Flow<List<User>> = flow {
        delay(400)
        emit(MockUsers.users)
    }
}

