package com.example.merchplace.presentation.widgets.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merchplace.shared.ui.LocalAsyncImage
import com.example.merchplace.domain.entities.FeedPost
import com.example.merchplace.shared.ui.Avatar
import com.example.merchplace.shared.ui.MerchCard
import com.example.merchplace.ui.theme.PrimaryRed
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.tooling.preview.Preview
import com.example.merchplace.data.datasource.mock.MockFeedPosts
import com.example.merchplace.ui.theme.MerchPlaceTheme

@Composable
fun FeedPost(
    post: FeedPost,
    modifier: Modifier = Modifier
) {
    MerchCard(modifier = modifier) {
        Column {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Avatar(
                    imageUrl = post.userAvatar,
                    size = 48
                )
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = post.userName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = formatTimestamp(post.timestamp),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Content
            Text(
                text = post.content,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 20.sp
            )
            
            // Images
            if (post.images.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                
                if (post.images.size == 1) {
                    LocalAsyncImage(
                        imageUrl = post.images.first(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        post.images.take(2).forEach { imageUrl ->
                            LocalAsyncImage(
                                imageUrl = imageUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(150.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            HorizontalDivider(color = MaterialTheme.colorScheme.outline)
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Лайк",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        text = "${post.likes}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Комментарий",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        text = "${post.comments}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Поделиться",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FeedPostPreview() {
    MerchPlaceTheme {
        FeedPost(post = MockFeedPosts.posts.first())
    }
}

private fun formatTimestamp(timestamp: Date): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp.time
    
    return when {
        diff < 60 * 1000 -> "только что"
        diff < 60 * 60 * 1000 -> "${diff / (60 * 1000)} мин назад"
        diff < 24 * 60 * 60 * 1000 -> "${diff / (60 * 60 * 1000)} ч назад"
        else -> SimpleDateFormat("dd MMM", Locale("ru")).format(timestamp)
    }
}

