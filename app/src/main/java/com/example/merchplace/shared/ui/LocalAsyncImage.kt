package com.example.merchplace.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.example.merchplace.shared.utils.getDrawableResourceUri
import com.example.merchplace.shared.utils.getResourceNameFromUrl

/**
 * AsyncImage компонент, который автоматически преобразует URL в локальные ресурсы,
 * если соответствующий файл существует в res/drawable
 */
@Composable
fun LocalAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    val context = LocalContext.current
    
    // Проверяем, является ли это URL или нужно использовать локальный ресурс
    val imageModel = if (imageUrl.startsWith("http")) {
        // Пытаемся найти локальный ресурс по имени файла из URL
        val resourceName = getResourceNameFromUrl(imageUrl)
        val resourceId = context.resources.getIdentifier(
            resourceName,
            "drawable",
            context.packageName
        )
        if (resourceId != 0) {
            // Локальный ресурс найден - используем его
            "android.resource://${context.packageName}/$resourceId"
        } else {
            // Локальный ресурс не найден - используем URL
            imageUrl
        }
    } else if (imageUrl.startsWith("android.resource://")) {
        // Уже локальный ресурс
        imageUrl
    } else {
        // Пустая строка или другой формат - используем placeholder
        imageUrl
    }
    
    AsyncImage(
        model = imageModel,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}

