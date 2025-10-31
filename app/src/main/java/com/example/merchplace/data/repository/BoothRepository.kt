package com.example.merchplace.data.repository

import com.example.merchplace.data.datasource.mock.MockBooths
import com.example.merchplace.domain.entities.Booth
import com.example.merchplace.domain.repository.IBoothRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BoothRepository : IBoothRepository {
    
    override fun getBooths(): Flow<List<Booth>> = flow {
        delay(200)
        emit(MockBooths.booths)
    }
    
    override suspend fun getBoothById(boothId: Int): Booth? {
        delay(200)
        return MockBooths.booths.find { it.id == boothId }
    }
}

