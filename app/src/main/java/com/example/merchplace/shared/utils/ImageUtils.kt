package com.example.merchplace.shared.utils

import android.content.Context

/**
 * Преобразует имя файла из res/drawable в URI для использования с Coil
 * @param fileName имя файла без расширения (например, "enamel_pin_furry")
 * @return URI строку для использования с AsyncImage
 */
fun getDrawableResourceUri(context: Context, fileName: String): String {
    val resourceId = context.resources.getIdentifier(
        fileName,
        "drawable",
        context.packageName
    )
    return if (resourceId != 0) {
        "android.resource://${context.packageName}/$resourceId"
    } else {
        // Fallback к placeholder, если ресурс не найден
        "android.resource://${context.packageName}/${android.R.drawable.ic_menu_gallery}"
    }
}

/**
 * Получает имя ресурса из URL (для обратной совместимости)
 * Преобразует URL GitHub в имя локального ресурса
 */
fun getResourceNameFromUrl(url: String): String {
    // Извлекаем имя файла из URL
    val fileName = url.substringAfterLast("/")
        .substringBeforeLast(".")
        .replace("-", "_") // Заменяем дефисы на подчеркивания
    return fileName
}

