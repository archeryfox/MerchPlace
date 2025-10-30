package com.example.merchplace.presentation.widgets.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merchplace.data.datasource.mock.MockUsers
import com.example.merchplace.domain.entities.User
import com.example.merchplace.shared.ui.MerchCard
import com.example.merchplace.ui.theme.MerchPlaceTheme

// Create custom icons for missing ones using existing icons
// Note: Material Icons might not have all icons, so we use alternatives

@Composable
fun ProfileStats(
    user: User,
    modifier: Modifier = Modifier
) {
    val stats = listOf(
        StatItem(Icons.Default.Person, "Подписчики", user.followers.toString()),
        StatItem(Icons.Default.AddCircle, "Подписки", user.following.toString()),
        StatItem(Icons.Default.ShoppingCart, "Продано", user.itemsSold.toString()),
        StatItem(Icons.Default.Star, "Рейтинг", String.format("%.1f", user.rating))
    )
    
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        stats.forEach { stat ->
            MerchCard(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = stat.icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stat.value,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stat.label,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

private data class StatItem(
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val label: String,
    val value: String
)

@Preview(showBackground = true)
@Composable
private fun ProfileStatsPreview() {
    MerchPlaceTheme {
        ProfileStats(user = MockUsers.users.first())
    }
}

