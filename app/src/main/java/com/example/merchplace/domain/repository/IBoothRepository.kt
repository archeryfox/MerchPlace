package com.example.merchplace.domain.repository

import com.example.merchplace.domain.entities.Booth
import kotlinx.coroutines.flow.Flow

interface IBoothRepository {
    fun getBooths(): Flow<List<Booth>>
    suspend fun getBoothById(boothId: Int): Booth?
}

