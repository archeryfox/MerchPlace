package com.example.merchplace.shared.ui

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.merchplace.shared.utils.getDrawableResourceUri
import com.example.merchplace.shared.utils.getResourceNameFromUrl

@Composable
fun Avatar(
    imageUrl: String,
    modifier: Modifier = Modifier,
    size: Int = 40
) {
    val context = LocalContext.current
    
    // Проверяем, является ли это URL или нужно использовать локальный ресурс
    val imageModel = if (imageUrl.startsWith("http")) {
        // Если это URL, используем его напрямую
        imageUrl
    } else if (imageUrl.startsWith("android.resource://")) {
        // Уже локальный ресурс
        imageUrl
    } else {
        // Пытаемся найти локальный ресурс по имени файла из URL
        val resourceName = getResourceNameFromUrl(imageUrl)
        getDrawableResourceUri(context, resourceName)
    }
    
    AsyncImage(
        model = imageModel,
        contentDescription = null,
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
private fun AvatarPreview() {
    Avatar(
        imageUrl = "https://via.placeholder.com/150",
        size = 64
    )
}

