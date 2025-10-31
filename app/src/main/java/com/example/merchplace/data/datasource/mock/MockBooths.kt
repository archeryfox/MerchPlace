package com.example.merchplace.data.datasource.mock

import com.example.merchplace.domain.entities.Booth

object MockBooths {
    private val users = MockUsers.users
    
    val booths = listOf(
        Booth(
            id = 1,
            name = "Стенд 42",
            seller = users[0],
            latitude = 55.7558,
            longitude = 37.6173,
            category = "Фурри-арт"
        ),
        Booth(
            id = 2,
            name = "Стенд 15",
            seller = users[1],
            latitude = 55.7560,
            longitude = 37.6175,
            category = "Фигурки"
        )
    )
}

