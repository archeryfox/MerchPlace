package com.example.merchplace.presentation.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merchplace.shared.ui.MerchCard
import com.example.merchplace.ui.theme.MerchPlaceTheme

@Composable
fun NotificationsScreen(
    modifier: Modifier = Modifier
) {
    val notifications = listOf(
        NotificationItem(
            id = 1,
            type = "bid",
            icon = Icons.Default.Notifications,
            title = "Новая ставка на ваш аукцион",
            message = "Пользователь @maria_art сделал ставку 2500₽ на 'Фигурка Sonic'",
            time = "5 минут назад",
            read = false
        ),
        NotificationItem(
            id = 2,
            type = "like",
            icon = Icons.Default.Favorite,
            title = "Новый лайк",
            message = "Алексей Волков лайкнул ваш пост",
            time = "1 час назад",
            read = false
        ),
        NotificationItem(
            id = 3,
            type = "comment",
            icon = Icons.Default.Email,
            title = "Новый комментарий",
            message = "@alexwolf: 'Отличная работа! Когда будет доступна для покупки?'",
            time = "2 часа назад",
            read = false
        ),
        NotificationItem(
            id = 4,
            type = "order",
            icon = Icons.Default.ShoppingCart,
            title = "Заказ оформлен",
            message = "Ваш заказ #12345 успешно оформлен и отправлен",
            time = "3 часа назад",
            read = true
        )
    )
    
    val unreadCount = notifications.count { !it.read }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Уведомления",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                if (unreadCount > 0) {
                    Text(
                        text = "$unreadCount непрочитанных",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            if (unreadCount > 0) {
                TextButton(onClick = { /* TODO */ }) {
                    Text("Прочитать все")
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notifications) { notification ->
                NotificationCard(notification = notification)
            }
        }
        
        Spacer(modifier = Modifier.height(80.dp)) // Space for bottom navigation
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationsScreenPreview() {
    MerchPlaceTheme {
        NotificationsScreen()
    }
}

data class NotificationItem(
    val id: Int,
    val type: String,
    val icon: ImageVector,
    val title: String,
    val message: String,
    val time: String,
    val read: Boolean
)

@Composable
private fun NotificationCard(
    notification: NotificationItem
) {
    MerchCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = { /* TODO */ }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Icon(
                    imageVector = notification.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = notification.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    if (!notification.read) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = notification.message,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = notification.time,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

