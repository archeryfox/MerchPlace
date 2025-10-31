package com.example.merchplace.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryRed,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = DarkBorder,
    onSecondary = TextPrimary,
    tertiary = AccentOrange,
    onTertiary = androidx.compose.ui.graphics.Color.White,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = DarkCard,
    onSurfaceVariant = TextSecondary,
    outline = DarkBorder,
    error = ErrorRed,
    onError = androidx.compose.ui.graphics.Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryRed,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = LightBorder,
    onSecondary = LightTextPrimary,
    tertiary = AccentOrange,
    onTertiary = androidx.compose.ui.graphics.Color.White,
    background = LightBackground,
    onBackground = LightTextPrimary,
    surface = LightSurface,
    onSurface = LightTextPrimary,
    surfaceVariant = LightCard,
    onSurfaceVariant = LightTextSecondary,
    outline = LightBorder,
    error = ErrorRed,
    onError = androidx.compose.ui.graphics.Color.White
)

@Composable
fun MerchPlaceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Автоматическое определение темы системы
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}