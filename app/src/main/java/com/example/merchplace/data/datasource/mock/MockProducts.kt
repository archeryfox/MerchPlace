package com.example.merchplace.data.datasource.mock

import com.example.merchplace.domain.entities.Product

object MockProducts {
    private val users = MockUsers.users
    
    val products = listOf(
        Product(
            id = 101,
            title = "Значок 'Furry Pride' эмалированный",
            description = "Эмалированный значок высокого качества. Размер 3см.",
            image = "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/enamel-pin-furry.jpg",
            price = 350,
            stock = 45,
            category = "Аксессуары",
            seller = users[0],
            rating = 4.8,
            reviews = 23
        ),
        Product(
            id = 102,
            title = "Стикерпак 'Anime Vibes' (20 шт)",
            description = "Набор из 20 водостойких стикеров с аниме персонажами.",
            image = "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/anime-sticker-pack.png",
            price = 500,
            stock = 120,
            category = "Стикеры",
            seller = users[1],
            rating = 5.0,
            reviews = 67
        ),
        Product(
            id = 103,
            title = "Артбук 'Furry Fantasy' 2024",
            description = "Коллекционный артбук с работами 30 художников. 120 страниц.",
            image = "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/artbook-fantasy.jpg",
            price = 1500,
            stock = 15,
            category = "Книги",
            seller = users[0],
            rating = 4.9,
            reviews = 12
        ),
        Product(
            id = 104,
            title = "Плюшевая игрушка 'Дракон' ручной работы",
            description = "Мягкая игрушка ручной работы, высота 25см.",
            image = "https://raw.githubusercontent.com/doggich/MerchPlace-vibe-protype/main/public/plush-dragon-toy.jpg",
            price = 2500,
            stock = 8,
            category = "Игрушки",
            seller = users[1],
            rating = 5.0,
            reviews = 34
        )
    )
}

